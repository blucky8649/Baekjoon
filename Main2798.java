import java.io.*;
import java.util.StringTokenizer;

public class Main2798{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int sum =0;

        st= new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int[] card = new int[n];
        int m = Integer.parseInt(st.nextToken());
        st= new StringTokenizer(br.readLine(), " ");

        for (int i = n-1 ; i>= 0 ; i--){
            card[i] = Integer.parseInt(st.nextToken());
        }

       for(int i = 0 ; i < n - 2 ; i++){
           for(int j = i+1; j < n - 1 ; j++ ){
               for(int z  = j+1 ; z < n ; z ++){
                   if(card[i] + card[j] + card[z] > m){
                       continue;
                   }else{
                       if ( sum <= card[i] + card[j] + card[z]){
                            sum = card[i] + card[j] + card[z];
                       }

                   }
               }
           }
       }
       System.out.println(sum);

    }
}