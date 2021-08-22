import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14500 {

    //--------------------------<Guide>---------------------------------------
    // 1. 'ㅗ'를 제외한 모든 도형은 모두 DFS를 활용한 빽트래킹으로 구현 가능하다.
    // 2, 'ㅗ' 의 경우의 수는 따로 구해서 계산해주자
    //------------------------------------------------------------------------


    static int N, M, map[][], result = Integer.MIN_VALUE;
    static boolean isVisited[][];
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    

    // ㅜ, ㅓ, ㅗ, ㅏ
    static int[][] ey = {{0, 0, 0, 1}, {0, 1, 2, 1}, {0, 0, 0, -1}, {0, 1, 2, 1}};
    static int[][] ex = {{0, 1, 2, 1}, {0, 0, 0, -1}, {0, 1, 2, 1}, {0, 0, 0, 1}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        isVisited = new boolean[N][M];

        for(int i = 0 ; i < N ; i ++){
            st= new StringTokenizer(br.readLine() , " ");
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j<M ;j++){
                DFS(j, i, 0, 0);
                Fy(j, i, 0);
                
            }
        }
        System.out.println(result);
    }
    static void DFS(int x, int y, int sum, int cnt){
        if(cnt ==  4){
            result = Math.max(result, sum);
            return;

        }

        for(int i = 0 ; i < 4 ; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < M && ny >= 0 && ny <N && !isVisited[ny][nx]){
                isVisited[ny][nx] = true;
                DFS(nx, ny, (sum + map[ny][nx]), cnt + 1);
                isVisited[ny][nx] = false; //백트래킹
            }
        }
    }
    static void Fy(int x, int y, int sum){

        // 1. 범위에 벗어나는지 체크하고 벗어나면 continue;
        // 2. 벗어나지 않으면 최댓값 키재기

        for(int i = 0 ; i < 4 ; i++){
            sum = 0; // 도형이 바뀔때마다 초기화해주어야 한다... 꼭.
            boolean out = false;
            for(int j = 0 ; j < 4 ; j++){
                int nx = x + ex[i][j];
                int ny = y + ey[i][j];

                if(!(nx >= 0 && nx < M && ny >= 0 && ny <N)){ //범위 밖이면
                    out = true;
                    continue;
                }
                sum += map[ny][nx];

            }
            if(!out){ //범위 안벗어나면 키재기 
                result = Math.max(result, sum);
            }
        }
    }
}
