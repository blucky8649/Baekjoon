import java.io.*;
import java.util.StringTokenizer;

public class Main7568{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        int[] length = new int[n];
        int[] weight = new int[n]; 
        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            weight[i] = x;
            length[i] = y;

        }
        for(int i = 0 ; i< n ; i++){
            int rank = 1;
            for(int j = 0 ; j < n ; j++){
                if(length[i] < length[j] && weight[i] < weight[j]){
                    rank = rank+1;
                }
            }
            System.out.print(rank + " ");
        }
    }
}