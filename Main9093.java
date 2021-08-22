import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main9093 {
    public static void main(String[] args) throws IOException{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        
        String[] arr;
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            
            while(st.hasMoreTokens()){
                String A = st.nextToken();
                arr = A.split("");
                for(int j = A.length()-1 ; j >= 0; j--){
                    sb.append(arr[j]);
                }
                sb.append(" ");
            }
         sb.append("\n"); 
        }
        System.out.print(sb.toString());
    }
    
}
