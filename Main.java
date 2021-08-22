import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    //1. 별을 찍을 도화지 제작
    public static char[][] board;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new char[n][2 * n];
        makeStarGroup(n, 0, n-1);
        printStarGroup(n);


    }
    // 2. 별 찍기
    public static void printStar(int y, int x){
        board[y][x] = '*';
        board[y + 1][x - 1] = board[y + 1][x + 1] = '*';
        board[y + 2][x - 2] = board[y + 2][x - 1] = board[y + 2][x] = board[y + 2][x + 1] = board[y + 2][x + 2] = '*';
    }
    // 3. 재귀함수
    public static void makeStarGroup(int n, int y, int x){
        if(n < 3){
            return;
        }
        
        if(n == 3){
            printStar(y, x);
            return;
        }
        makeStarGroup(n / 2,y ,x  );
        makeStarGroup(n / 2, y +  n / 2 , x - n / 2);
        makeStarGroup(n / 2, y +  n / 2 , x + n / 2);    
    }
    // 4. 별 출력
    public static void printStarGroup(int n){
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n*2 ; j++){
                if(board[i][j] == 0){
                    sb.append(' ');
                }else{
                    sb.append(board[i][j]);
                }
                
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
