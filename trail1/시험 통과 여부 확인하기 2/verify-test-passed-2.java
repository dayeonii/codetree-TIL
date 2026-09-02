import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int result = 0;
        for(int i=0; i<N; i++) {
            int sum = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                sum += Integer.parseInt(st.nextToken());
            }
            if(sum / 4 >= 60) {
                System.out.println("pass");
                result++;
                continue;
            }
            System.out.println("fail");
        }
        System.out.println(result);
    }
}