import java.io.*;
import java.util.StringTokenizer;




public class Main14889 {
    public static int min = 999999999;
    public static int[][] arr;
    public static int n;
    public static boolean[] isUsed;
    public static int[] team;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr = new int[n][n];
        isUsed = new boolean[n]; 

        for(int i = 0 ; i < n ; i++){
            st= new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < n ; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

            }
            
        }
        FNC(0, 0);
        System.out.println(min);
    }
    public static void FNC(int at, int k){
        if(k==n / 2){
            
            diff();
            return;
        }

        for(int i = at ; i < n ; i++){
            if(!isUsed[i]){
                isUsed[i] = true;
                FNC(i +1, k + 1);
                isUsed[i] = false;

            }
        }

    }
    public static void diff(){
        
        int sum=0;
        for(int i = 0 ; i < n-1 ; i++){
            for(int j = i+1 ; j < n ;j++){
                if(isUsed[i] && isUsed[j]){
                    sum += arr[i][j];
                    sum += arr[j][i];
                }

                else if(!isUsed[i] && !isUsed[j]){
                    sum -= arr[i][j];
                    sum -= arr[j][i];
                }

                
            }
        }
        if(Math.abs(sum) < min){
            min = Math.abs(sum);
        }
    
    }
    
}
