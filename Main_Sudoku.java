import java.io.*;
import java.util.StringTokenizer;


public class Main_Sudoku {
    public static int[][] arr = new int[9][9];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0 ; i < 9 ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < 9 ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Sudoku(0, 0);

        
    }
    public static void Sudoku(int row, int col){
        if (col == 9){
            Sudoku(row + 1, 0);
            return;
        }

        if (row == 9){
            for(int i = 0 ; i < 9 ; i++){
                for(int j = 0 ; j < 9 ; j++ ){
                    System.out.print(arr[i][j]  + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }
        

        if(arr[row][col] == 0){
            for(int i = 1 ; i<=9 ; i++){
                if(check(row, col, i)){
                    arr[row][col] = i;
                    Sudoku(row, col+1);
                }
            }
            arr[row][col] = 0;
            return;
        }

        Sudoku(row, col+1);
    }


    public static boolean check(int row, int col, int val){
        for(int i = 0 ; i < 9 ; i++){
            if(arr[row][i] == val){
                return false;
            }
        }

        for(int i = 0 ; i < 9 ; i++){
            if(arr[i][col] == val){
                return false;
            }
        }
        int set_row = (row / 3 ) * 3;
        int set_col = (col / 3 ) * 3;

        for(int i = set_row ; i < set_row + 3 ; i++){
            for(int j = set_col ; j < set_col + 3 ; j++){
                if(arr[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }
}