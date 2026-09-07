import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i=0; i<=N-3; i++) {
            for(int j=0; j<=N-3; j++) {
                int sum = arr[i][j] + arr[i][j+1] + arr[i][j+2]
                        + arr[i+1][j] + arr[i+1][j+1] + arr[i+1][j+2]
                        + arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2];
                if (sum>max) {
                    max = sum;
                }
            }
        }

        System.out.println(max);
    }
}