import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        printStar(N);
    }

    private static void printStar(int N) {
        if(N==0) {
            return;
        }

        printStar(N-1);
        for(int i=0; i<N; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}