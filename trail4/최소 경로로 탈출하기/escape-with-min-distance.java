import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int result;
    static int[][] map;
    static boolean[][] visited;

    static int[][] delta = { {-1,0}, {1,0}, {0,-1}, {0,1} };

    static class Node {
        int x;
        int y;
        int step;

        Node(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==0) {
                    visited[i][j] = true;
                }
            }
        }

        result = -1;
        BFS();

        System.out.println(result);
    }

    private static void BFS() {
        Queue<Node> queue = new ArrayDeque<>();

        queue.add(new Node(0,0,0));
        visited[0][0] = true;

        while(!queue.isEmpty()) {
            Node current = queue.poll();

            if(current.x == N-1 && current.y == M-1) {
                result = current.step;
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = current.x + delta[i][0];
                int ny = current.y + delta[i][1];

                if(canGo(nx, ny)) {
                    queue.add(new Node(nx, ny, current.step+1));
                    visited[nx][ny] = true;
                }
            }
            
        }
    }

    private static boolean canGo(int x, int y) {
        return x>=0 && x<N && y>=0 && y<M && map[x][y]==1 && !visited[x][y];
    }
}
