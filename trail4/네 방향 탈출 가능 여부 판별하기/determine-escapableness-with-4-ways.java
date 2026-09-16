import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Main {

    static int N, M;
    static int result;
    static int map[][];
    static boolean visited[][];

    static int delta[][] = { {-1,0}, {1,0}, {0,-1}, {0,1} };

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0) {
                    visited[i][j]=true;
                }
            }
        }

        result = 0;
        BFS();

        System.out.println(result);
    }

    private static void BFS() {
        int startX = 0;
        int startY = 0;

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(startX, startY));
        visited[startX][startY] = true;

        while(!queue.isEmpty()) {
            Pair current = queue.poll();

            if(current.x==N-1 && current.y == M-1) {
                result = 1;
                return;
            }

            
                for(int i=0; i<4; i++) {
                    int nx = current.x + delta[i][0];
                    int ny = current.y + delta[i][1];
                    if(canGo(nx, ny) && !visited[nx][ny]) {
                        queue.add(new Pair(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            
        }

        
    }

    private static boolean canGo(int x, int y) {
        return x>=0 && x<N && y>=0 && y<M && map[x][y]==1;
    }
}