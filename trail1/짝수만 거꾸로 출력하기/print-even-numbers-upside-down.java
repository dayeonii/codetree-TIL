import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Integer> evenArr = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (number % 2 == 0) {
                evenArr.add(number);
            }
        }

        for (int i = evenArr.size() - 1; i >= 0; i--) {
            if (i == 0) {
                sb.append(evenArr.get(i));
                break;
            }
            sb.append(evenArr.get(i)).append(' ');
        }

        System.out.print(sb);
    }
}
