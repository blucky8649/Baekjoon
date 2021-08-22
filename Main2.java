import java.io.*;

public class Main2{
    // 1. 별을 찍을 도화지 선언
    public static char board[][] ;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        makeRecGroup(n,0, 0);
        printRec(n);



    }
    // 2. 도형 그리기
    public static void makeRec(int y, int x){
        board[y][x] = board[y][x+1] = board[y][x+2] = '*';
        board[y+1][x] = board[y+1][x+2] = '*';
        board[y+2][x] = board[y+2][x+1] = board[y+2][x+2] = '*';
    }
    
    // 3. 재귀함수
    public static void makeRecGroup(int n, int y, int x){
        if(n == 3){
            makeRec(y, x);
            return;
        }

        makeRecGroup(n / 3, y , x);
        makeRecGroup(n / 3, y , x + n / 3);
        makeRecGroup(n / 3, y, x + n / 3 * 2);

        makeRecGroup(n / 3, y + n / 3, x);
        makeRecGroup(n / 3, y + n / 3 , x + n / 3 * 2);

        makeRecGroup(n / 3, y + n / 3 * 2 , x);
        makeRecGroup(n / 3, y + n / 3 * 2 , x + n / 3);
        makeRecGroup(n / 3, y + n / 3 * 2 , x + n / 3 * 2);
    }

    // 4. 도형 출력
    public static void printRec(int n){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n ; i++){
            for(int j = 0 ; j<n ; j++){
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