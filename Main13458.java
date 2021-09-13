import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main13458 {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;
        long N = Integer.parseInt(br.readLine());
        long[] CR = new long[(int) N];
        StringTokenizer st = new StringTokenizer(br. readLine(), " ");
        for(int i = 0 ; i < N ; i++){
            CR[i] = Integer.parseInt(st.nextToken());
            
        }
        st = new StringTokenizer(br.readLine(), " ");

        long master = Integer.parseInt(st.nextToken());
        long sub = Integer.parseInt(st.nextToken());

        for(int i= 0 ; i < CR.length ; i++){
            CR[i] = CR[i] - master;
            result++;

            if(CR[i] <= 0) continue;
            if(CR[i] % sub == 0){
                result += CR[i] / sub;
                
            }else{
                result += CR[i] / sub;
                result++;
            }
        }
        System.out.println(result);
        //출력

    }
    
}
