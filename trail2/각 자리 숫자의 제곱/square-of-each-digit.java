import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = numbers(n);
        System.out.println(result);
    }

    private static int numbers(int N) {
        if(N<10) {
            return N*N;
        }

        return numbers(N/10) + numbers(N%10);
    }
}