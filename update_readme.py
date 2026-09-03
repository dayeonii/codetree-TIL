import os
import re

# 트레일별 전체 정확한 정보 (알려주신 데이터 적용)
TRAIL_TOTALS = {
    "Trail 1": {"name": "Novice Low (프로그래밍 기초)", "chapters": 9, "lessons": 87, "total_problems": 396},
    "Trail 2": {"name": "Novice Mid (프로그래밍 연습)", "chapters": 10, "lessons": 29, "total_problems": 241},
    "Trail 3": {"name": "Novice High (자료구조 알고리즘)", "chapters": 10, "lessons": 52, "total_problems": 242},
    "Trail 4": {"name": "Intermediate Low (알고리즘 입문)", "chapters": 6, "lessons": 19, "total_problems": 130},
    "Trail 5": {"name": "Intermediate Mid (알고리즘 기본)", "chapters": 5, "lessons": 18, "total_problems": 176},
    "Trail 6": {"name": "Intermediate High (알고리즘 실전)", "chapters": 5, "lessons": 17, "total_problems": 149},
}

TOTAL_ALL_PROBLEMS = sum(t["total_problems"] for t in TRAIL_TOTALS.values()) # 총 1,334문제

def make_progress_bar(solved, total, length=12):
    """퍼센트에 기반한 직관적인 프로그레스 바 생성"""
    if total == 0:
        return "░" * length
    percent = min(1.0, solved / total)
    filled = int(length * percent)
    return "█" * filled + "░" * (length - filled)

def parse_problem_readme(file_path):
    """문제 폴더 내부 README.md에서 Trail, Chapter, Lesson 파싱"""
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
            match = re.search(r'Trail\s*(\d+)\s*/\s*([^/\n]+)\s*/\s*([^/\n]+)', content, re.IGNORECASE)
            if match:
                trail_key = f"Trail {match.group(1)}"
                chapter = match.group(2).strip()
                lesson = match.group(3).strip()
                return trail_key, chapter, lesson
    except Exception:
        pass
    return None

def main():
    root_dir = "."
    stats = {f"Trail {i}": {"solved_cnt": 0, "chapters": {}} for i in range(1, 7)}
    total_solved = 0
    current_active_trail = "Trail 1"

    # 문제 폴더 탐색
    for root, dirs, files in os.walk(root_dir):
        dirs[:] = [d for d in dirs if not d.startswith('.')]

        if "README.md" in files and root != ".":
            readme_path = os.path.join(root, "README.md")
            parsed = parse_problem_readme(readme_path)

            if parsed:
                trail_key, chapter, lesson = parsed
                if trail_key in stats:
                    stats[trail_key]["solved_cnt"] += 1
                    total_solved += 1
                    current_active_trail = trail_key

                    if chapter not in stats[trail_key]["chapters"]:
                        stats[trail_key]["chapters"][chapter] = 0
                    stats[trail_key]["chapters"][chapter] += 1

    # 전체 진행률 계산
    overall_percent = round((total_solved / TOTAL_ALL_PROBLEMS) * 100, 1)
    overall_bar = make_progress_bar(total_solved, TOTAL_ALL_PROBLEMS, length=15)

    badge_trail_name = TRAIL_TOTALS[current_active_trail]["name"].replace(" ", "_")

    md = f"""# 🌲 CodeTree TIL (알고리즘 감시 대시보드)

<p align="left">
  <img src="https://img.shields.io/badge/Overall_Progress-{total_solved}_{TOTAL_ALL_PROBLEMS}_({overall_percent}%25)-blue?style=for-the-badge&logo=codetree" />
  <img src="https://img.shields.io/badge/Current_Trail-{badge_trail_name}-green?style=for-the-badge" />
</p>

### 🎯 전체 커리큘럼 달성도
`[{overall_bar}] {overall_percent}% ({total_solved} / {TOTAL_ALL_PROBLEMS} 문제 해결)`

---

## 📊 Trail별 상세 진도율

"""

    for t_key, t_info in TRAIL_TOTALS.items():
        t_data = stats[t_key]
        solved_cnt = t_data["solved_cnt"]
        total_p = t_info["total_problems"]
        percent = round((solved_cnt / total_p) * 100, 1)
        bar = make_progress_bar(solved_cnt, total_p, length=10)

        if solved_cnt > 0:
            status_emoji = "🔥"
            md += f"### {status_emoji} `{t_key}` - {t_info['name']}\n"
            md += f"- **진행률:** `[{bar}] {percent}%` ({solved_cnt} / {total_p} 문제)\n"
            md += f"- **풀이한 챕터 현황:**\n"
            for ch_name, count in t_data["chapters"].items():
                md += f"  * 🟢 **{ch_name}**: `{count}문제 완료`\n"
            md += "\n"
        else:
            md += f"### ⚪ `{t_key}` - {t_info['name']}\n"
            md += f"- **진행률:** `[{bar}] 0%` (0 / {total_p} 문제)\n\n"

    md += """---
> 🤖 *이 대시보드는 GitHub Actions를 통해 문제 제출 시 실시간 자동 반영됩니다.*
"""

    with open("README.md", "w", encoding="utf-8") as f:
        f.write(md)

if __name__ == "__main__":
    main()
