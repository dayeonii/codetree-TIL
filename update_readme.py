import os
import re

# 6개 Trail 기본 정보 정의
TRAILS_INFO = {
    "Trail 1": "Novice Low (프로그래밍 기초)",
    "Trail 2": "Novice Mid (프로그래밍 연습)",
    "Trail 3": "Novice High (자료구조 알고리즘)",
    "Trail 4": "Intermediate Low (알고리즘 입문)",
    "Trail 5": "Intermediate Mid (알고리즘 기본)",
    "Trail 6": "Intermediate High (알고리즘 실전)"
}

def create_progress_bar(count, goal=5, length=10):
    """숫자에 따른 [████░░░░░░] 형태의 프로그레스 바 생성"""
    if count == 0:
        return "░" * length
    # 챕터당 평균 5문제를 기준으로 한 백분율 계산 (상황에 따라 시각적 비율 제공)
    ratio = min(1.0, count / goal)
    filled = int(length * ratio)
    return "█" * filled + "░" * (length - filled)

def parse_problem_readme(file_path):
    """문제 폴더 내부 README.md에서 Trail, Chapter, Lesson 파싱"""
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
            # "Trail 1 / 챕터명 / 레슨명" 또는 "Trail1 / 챕터명 / 레슨명" 형태 정규식 파싱
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

    # 레포지토리 내부 탐색
    for root, dirs, files in os.walk(root_dir):
        # 숨김 폴더(.github, .git 등) 제외
        dirs[:] = [d for d in dirs if not d.startswith('.')]

        # 최상단 README.md가 아닌 각 문제 폴더의 README.md 검사
        if "README.md" in files and root != ".":
            readme_path = os.path.join(root, "README.md")
            parsed = parse_problem_readme(readme_path)

            if parsed:
                trail_key, chapter, lesson = parsed
                if trail_key in stats:
                    stats[trail_key]["solved_cnt"] += 1
                    total_solved += 1
                    current_active_trail = trail_key  # 가장 최근 풀이 진행 중인 Trail

                    if chapter not in stats[trail_key]["chapters"]:
                        stats[trail_key]["chapters"][chapter] = 0
                    stats[trail_key]["chapters"][chapter] += 1

    # README Markdown 생성
    badge_trail_encoded = TRAILS_INFO[current_active_trail].replace(" ", "_")
    
    md = f"""# 🌲 CodeTree TIL (학습 현황)

<p align="left">
  <img src="https://img.shields.io/badge/Current_Trail-{badge_trail_encoded}-green?style=for-the-badge&logo=codetree" />
  <img src="https://img.shields.io/badge/Total_Solved-{total_solved}개-blue?style=for-the-badge&logo=github" />
</p>

---

## 📊 Trail별 진행 및 챕터 현황

"""

    for t_key, t_name in TRAILS_INFO.items():
        t_data = stats[t_key]
        solved_cnt = t_data["solved_cnt"]
        chapters = t_data["chapters"]

        if solved_cnt > 0:
            status_icon = "🔥"
            md += f"### {status_icon} `{t_key}` - {t_name}\n"
            md += f"- **총 풀이 문제 수:** `{solved_cnt}개`\n\n"
            
            for ch_name, count in chapters.items():
                p_bar = create_progress_bar(count)
                md += f"  * 🟢 **{ch_name}**: `{count}문제` `[{p_bar}]`\n"
            md += "\n"
        else:
            md += f"### ⚪ `{t_key}` - {t_name}\n"
            md += f"- *아직 제출된 풀이가 없습니다.*\n\n"

    md += """---
> 🤖 *이 README는 GitHub Actions를 통해 문제 해결 시 자동으로 업데이트됩니다.*
"""

    # 메인 README.md 작성
    with open("README.md", "w", encoding="utf-8") as f:
        f.write(md)

if __name__ == "__main__":
    main()