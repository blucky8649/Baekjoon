import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14890 {
    static int N, L, map[][], cnt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ;j < N ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0 ; i < N ; i++){
          if(check(i, 0)) cnt ++;
          if(check(i, 1)) cnt ++;  
        }
        System.out.println(cnt);
    }
    static boolean check(int index, int dir){
        /*
        * <해당 길이 지나갈 수 있는 길인지 검사>
        * 1. 경사차이가 2이상 나면 false 반환
        * 2. 오르막길인 경우와, 내리막길인 경우를 생각해야함
        * 3. 경사로는 중복으로 놓을 수 없다 (isVisited로 경사로 표시를 해줌)
        */
        boolean[] isVisited = new boolean[N];
        int arr[] = new int [N];
        switch(dir){
            case 0 : // 세로
                for(int i = 0 ; i < N ; i++){
                    arr[i] = map[i][index];
                 }

                 
                break;
            case 1 :  // 가로
                for(int i = 0 ; i < N ; i++){
                    arr[i] = map[index][i];
                }
                break;
            default : return false;
        }
        for(int i = 0 ; i < N - 1 ; i++){
            if(arr[i] == arr[i + 1]) {
                continue;
            }else{
                // 1) 내리막길인 경우
                if(arr[i] - arr[i + 1] == 1){
                    for(int j = i + 1 ; j <= i + L ; j++){
                       if( j >= N || arr[j] != arr[i + 1] || isVisited[j] ) {
                           return false;
                       }
                       isVisited[j] = true;

                    }
                }
                // 2) 오르막길인 경우
                else if(arr[i] - arr[i + 1] == -1){
                   for(int j = i  ; j > i - L ; j--){
                      if(j < 0 || arr[j] != arr[i] || isVisited[j]){
                        
                        return false;
                      } 
                      isVisited[j] = true;
                   }
               }else{
                   return false;
               }

            }
        }
        return true;
    }
}
