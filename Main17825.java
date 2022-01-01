import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


class Yoot {
    // 다음 칸으로 가는 칸을 지정해줌
    int val;
    int nextRed;
    int nextBlue;
    Yoot (int val, int nextRed, int nextBlue) {
        this.val = val;
        this.nextRed = nextRed;
        this.nextBlue = nextBlue;
    }
}

class Horse {
    int location;
    int score;
    Horse (int location, int score) {
        this.location = location;
        this.score = score;
    }
}

public class Main17825 {
    static int ans= 0;
    static Yoot[] board;
    static int[] dice = new int[11];
    static int[] move = new int[11];
    static int[] map = new int[33];
    static Horse[] horse = new Horse[5]; // 4개의 말
    static int max = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        board = new Yoot[33];
        set_board();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1 ; i <= 10 ; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        DFS_backtracting(1);
        System.out.println(max);

        
    }
    static void DFS_backtracting(int depth) {
        // 주사위를 다돌았으면 제일 큰 값 찾아내기
        if (depth == 11) {
            max = Math.max(max, ans);
            return;
        }
        // 1. 말을 고른다.
        for (int i = 1 ; i <= 4 ; i++) {
            int location = horse[i].location; // 원래 말이 있던 위치(백트래킹을 위해 사용)
            if(board[horse[i].location].nextRed == 100) {
                continue;
            }
            if(board[horse[i].location].nextBlue != 0) {
                int nb = board[horse[i].location].nextBlue;
                for (int a= 1 ; a < dice[depth] ; a++) {
                    if (nb == 32) {
                        break;
                    }
                    nb = board[nb].nextRed;
                }
                // 말이 이동 가능하면
                if (nb == 32 || map[nb] == 0) {

                    map[location]--; // 원래 있던 위치 감소시키고
                    map[nb] ++; // 새로 갈 위치 추가
                    horse[i].location = nb;
                    horse[i].score += board[nb].val;
                    ans += board[nb].val;
                    move[depth] = i;
                    DFS_backtracting(depth+1);
                    ans -= board[nb].val;
                    move[depth] = 0;
                    map[location]++;
                    map[nb]--;
                    horse[i].location = location;
                    horse[i].score -= board[nb].val;
                }
                
                continue;
            }

            if (board[horse[i].location].nextRed != 0) {
                int nb = board[horse[i].location].nextRed;
                for (int a= 1 ; a < dice[depth] ; a++) {
                    if (nb == 32) {
                        break;
                    }
                    nb = board[nb].nextRed;
                }
                // 말이 이동 가능하면
                if (nb == 32 || map[nb] == 0) {
                    
                    map[location]--; // 원래 있던 위치 감소시키고
                    map[nb] ++; // 새로 갈 위치 추가
                    horse[i].location = nb;
                    horse[i].score += board[nb].val;
                    move[depth] = i;
                    ans += board[nb].val;
                    DFS_backtracting(depth+1);
                    ans -= board[nb].val;
                    move[depth] = 0; 
                    map[location]++;
                    map[nb]--;
                    horse[i].location = location;
                    horse[i].score -= board[nb].val;
                }
            }

            
            
        }
    }
    // 윳판 만들기!
    static void set_board() {
        int val = 2;
        board[0] = new Yoot(0, 1, 0); // 출발점
        for (int i = 1 ; i < 20 ; i++) {
            board[i] = new Yoot(val, i+1, 0);
            val += 2;
        }
        board[5].nextBlue = 21;
        board[10].nextBlue = 28;
        board[15].nextBlue = 27;

        board[20] = new Yoot(40, 32, 0); // 100은 도착점을 의미

        board[21] = new Yoot(13, 22, 0);
        board[22] = new Yoot(16, 23, 0);
        board[23] = new Yoot(19, 24, 0);
        board[24] = new Yoot(25, 30, 0);

        board[27] = new Yoot(28, 26, 0);
        board[26] = new Yoot(27, 25, 0);
        board[25] = new Yoot(26, 24, 0);


        board[28] = new Yoot(22, 29, 0);
        board[29] = new Yoot(24, 24, 0);

        board[30] = new Yoot(30, 31, 0);
        board[31] = new Yoot(35, 20, 0);
        board[32] = new Yoot(0, 100, 0); //도착

        horse[0] = new Horse(0, 0);
        horse[1] = new Horse(0, 0);
        horse[2] = new Horse(0, 0);
        horse[3] = new Horse(0, 0);
        horse[4] = new Horse(0, 0);
        map[0] = 4; // 말 4마리 시작.
    }
}
