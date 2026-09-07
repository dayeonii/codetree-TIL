import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        print(N);
    }

    private static void print(int N) {
        if(N==0) {
            return;
        }
        System.out.print(N+" ");
        print(N-1);
        System.out.print(N+" ");
    }
}