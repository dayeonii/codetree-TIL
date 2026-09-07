import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        print1(N);
        System.out.println();
        print2(N);
    }

    public static void print1(int N) {
        if(N==0) {
            return;
        }

        print1(N-1);
        System.out.print(N+" ");
    }

public static void print2(int N) {
        if(N==0) {
            return;
        }

        System.out.print(N+" ");
        print2(N-1);
    }    
}