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

    private static void print(int n) {
        if(n==0) {
            return;
        }
        print(n-1);
        System.out.println("HelloWorld");
    }
}