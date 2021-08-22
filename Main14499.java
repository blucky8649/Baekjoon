import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14499 {
    static int map[][], dice[] = new int[7], N, M, dice_x, dice_y, K;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1 , 0, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dice_y = Integer.parseInt(st.nextToken());
        dice_x = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        


        // 1. 맵에 숫자 채우기
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 2. 주사위 굴리기
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < K ; i ++){
            int cmd = Integer.parseInt(st.nextToken());
            int nx = dice_x + dx[cmd - 1];
            int ny = dice_y + dy[cmd - 1];

            if(nx >= 0 && ny >= 0 && nx < M && ny < N){
                Dice(cmd);
                if (map[ny][nx] == 0) {
                    map[ny][nx] = dice[6];
                } else {
                    dice[6] = map[ny][nx];
                    map[ny][nx] = 0;
                }

                dice_x = nx;
                dice_y = ny;
                sb.append(dice[1]).append('\n'); // 윗면 값 출력
            }

        }

        System.out.print(sb.toString());
    }
    static void Dice(int cmd){

        //--------------<Guide>---------------------
        // cmd 1~4 까지 각각 동, 서, 남, 북
        // dice[6] = 밑면, dice[1] = 윗면 
        // dice[5] = 앞면, dice[2] = 뒷면
        // dice[4] = 오른쪽면, dice[3] = 왼쪽면
        // dice 배열을 복사하여 각각의 면을 바뀌준다.
        //------------------------------------------



        int clone[] = new int[7];
        clone = dice.clone(); // dice 의 수를 바꿔주기 위하여 배열을 복사한다.
        switch(cmd){
            case 1: 
            /*
            * ----------------<동쪽일 때>--------------------
            * 1. 오른쪽 면이 밑으로 가고, 왼쪽 면이 제일 위로 감.
            * 2. 앞뒷면은 변함 없음.
            * 3. 밑면은 오른쪽면으로, 윗면은 왼쪽면으로 감.
            * -----------------------------------------------
            */
                dice[1] = clone[2];
                dice[3] = clone[1];
                dice[6] = clone[3];
                dice[2] = clone[6];
                break;
            case 2: 
            /*
            * ----------------<서쪽일 때>--------------------
            * 1. 왼쪽 면이 밑으로 가고, 오른쪽 면이 제일 위로 감.
            * 2. 앞뒷면은 변함 없음.
            * 3. 밑면은 왼쪽 면으로, 윗면은 오른쪽 면으로 감.
            * -----------------------------------------------
            */
                dice[1] = clone[3];
                dice[2] = clone[1];
                dice[6] = clone[2];
                dice[3] = clone[6];
                break;
            case 3:
            /*
            * ----------------<북쪽일 때>--------------------
            * 1. 뒷면이 밑으로 가고, 앞면이 제일 위로 감.
            * 2. 오른쪽, 왼쪽면은 변함 없음.
            * 3. 밑면은 앞 면으로, 윗면은 뒷쪽 면으로 감.
            * -----------------------------------------------
            */
                dice[4] = clone[1];
                dice[6] = clone[4];
                dice[5] = clone[6];
                dice[1] = clone[5];
                break;
            case 4:
            /*
            * ----------------<남쪽일 때>--------------------
            * 1. 앞면이 밑으로 가고, 뒷면이 제일 위로 감.
            * 2. 오른쪽, 왼쪽면은 변함 없음.
            * 3. 밑면은 뒷면으로, 윗면은 앞면으로 감.
            * -----------------------------------------------
            */
                dice[5] = clone[1];
                dice[6] = clone[5];
                dice[4] = clone[6];
                dice[1] = clone[4];
                break;
        }
    }
}
