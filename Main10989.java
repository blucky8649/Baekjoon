
import java.io.*;

public class Main10989 {
    
    public static void main(String[] args) throws IOException {
        int[] count = new int[10001];

         BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        for(int i = 0 ; i < n ; i++){
          
            count[Integer.parseInt(br.readLine())] ++;
        }
        br.close();
        for(int i = 0 ; i < 10001 ; i++){
            for(int j = 0 ; j < count[i]; j++){
                System.out.println(i);
            }
            }
        }
        


    
}
