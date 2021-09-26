import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main20365 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] colors= new int[2];

        String str = br.readLine();

        if(str.charAt(0) == 'B'){
            colors[0]++;
        }else{
            colors[1]++;
        }


        for(int i = 1 ; i < n ; i++){
            if(str.charAt(i) == 'B'){
                colors[0]++;
                if(str.charAt(i-1) == 'B'){
                    colors[0]--;
                }
            }else{
                colors[1]++;
                if(str.charAt(i-1) != 'B'){
                    colors[1]--;
                }
            }
        }
        
        System.out.println(Math.min(colors[0], colors[1]) + 1);
    }
}
