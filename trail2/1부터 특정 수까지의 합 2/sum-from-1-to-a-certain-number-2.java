import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = fib(n);
        System.out.println(result);
    }

    private static int fib(int N) {
        if(N==1) {
            return 1;
        }

        return fib(N-1) + N;
    }
}