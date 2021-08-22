import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15684 {
    // 백준 15684번 : 사다리조작
    // 1. 사다리를 2차원 배열형태로 설치한다 (다음칸으로 가는 사다리는 2, 이전칸은 1)
    // 2. DFS백트래킹으로 하나하나 경우의수를 다 대입하여 사다리를 최대 3개까지 설치한다.
    // 3. check 함수를 사용하여 1부터 N 까지 모든 사다리가 제자리로 되돌아오면 성공, 아니면 실패



    static int N, M, H, ladder[][], max = 0;
    static boolean ok; // 루프가 끝났는지..
    static int[] dy = {0, -1, 1};
    static int[] dx = {0, -1, 1}; 
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H+1][N+1];
        for(int i = 0 ; i < M ; i++){
            st= new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = 2; // 다음칸으로 가는 사다리 
            ladder[a][b + 1] = 1; // 이전칸으로 가는 사다리 

        }
        for (int i = 0; i <= 3; i++) {
            max = i;
            setLadder(1, 0);
            if(ok) break;
        }
        setLadder(1, 0);
        System.out.println(ok ? max : -1);
        
    }
    static void setLadder(int x, int k){
        if(ok) return; 
        if(max == k) {
            if(check()) {
                ok = true; // 루프 종료 알림
            }
            return ;
        }

        for(int i = x ; i <= H ; i++){
            for(int j = 1 ; j < N ; j++){
                if(ladder[i][j] == 0 && ladder[i][j+1] == 0){
                    ladder[i][j] = 2;
                    ladder[i][j+1] = 1;
                    setLadder(i, k + 1);
                    ladder[i][j] = ladder[i][j+1] = 0; //백트래킹
                   
                }
            }
        }
    }

    static boolean check(){
        for(int i = 1 ; i <= N ; i++){
            int check = i;
            for(int j = 1; j <= H ; j++){
                
                if(ladder[j][check] == 1 ){
                    check -= 1;
                }
                else if(ladder[j][check] == 2){
                    check += 1;
                }
            }
            if(i != check){
                return false;
            }
        }
        return true;
    }
    
}
