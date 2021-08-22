import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main12100 {
    static int[][] map;
    static int n;
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        answer= Integer.MIN_VALUE;
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < n ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        game(0);

        System.out.println(answer);
    }
    
    static void game(int cnt){
        if(cnt == 5){
            findMax();
            
            return;
        }

        int[][] clone  = new int[n][n];
        for(int i =0 ; i< n ; i++){
            clone[i] = map[i].clone();
        }

        for(int i = 0 ; i < 4 ; i++){
            move(i);
            game(cnt + 1);

            for(int j= 0 ; j < n ; j++){
                map[j] = clone[j].clone();
            }
        }

    }
    static void move(int dir){
        switch(dir){
            //위
            case 0: 
                for(int i =0 ; i < n ; i++){
                    int block = 0;
                    int index = 0;
                    for(int j = 0 ; j < n ; j++){
                        if(map[j][i] != 0){
                            if(block == map[j][i]){
                                map[index - 1][i] = block * 2;
                                block = 0;
                                map[j][i] = 0;
                            }
                            else{
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i] = block;
                                index++;
                            }
                        }
                    }
                }    
            break;
            //아래
            case 1: 
                for(int i = 0 ; i< n ; i++){
                    int block = 0;
                    int index = n -1;
                    for(int j = n -1 ; j >= 0 ; j--){
                        if(map[j][i] != 0){
                            if(block == map[j][i]){
                                map[index + 1][i] = block * 2;
                                map[j][i] = 0;
                                block = 0;
                            }else{
                                block = map[j][i];
                                map[j][i] = 0;
                                map[index][i]= block;
                                index --;
                            }
                        }
                    }
                }
                break;
                //왼쪽
            case 2:
                for(int i = 0 ; i< n ; i++){
                    int block = 0;
                    int index = 0;
                    for(int j = 0 ; j < n ; j++){
                        if(map[i][j] != 0){
                            if(block == map[i][j]){
                                map[i][index - 1] = block * 2;
                                map[i][j] = 0;
                                block = 0;
                            }else{
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][index] = block;
                                index ++;
                            }
                        }
                    }
                }
                break;
                //오른쪽
            case 3:
                for(int i = 0 ; i < n ; i++){
                    int block = 0;
                    int index = n -1;
                    for(int j = n - 1 ; j >= 0 ; j--){
                        if(map[i][j] != 0){
                            if(block == map[i][j]){
                                map[i][index + 1] = block * 2;
                                map[i][j] = 0;
                                block = 0; 
                            }else{
                                block = map[i][j];
                                map[i][j] = 0;
                                map[i][index] = block;
                                index--;
                            }
                        }
                    }
                }
                break;
        }
    }

    public static void findMax() {
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++){
                answer = Math.max(answer, map[i][j]);
                
            }
            
                
    }
}
