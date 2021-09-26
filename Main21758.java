import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main21758 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] honey = new int[n];
        int max = 0;
        int sum = 0;
        for(int i = 0 ; i < n ; i++){
            honey[i] = Integer.parseInt(st.nextToken());
            sum += honey[i];
        }
        int[] num = new int[6];
        num[0] = sum - (honey[0] + honey[n - 1]);
        num[1] = sum - (honey[0] + honey[n / 2]);
        num[2] = sum - (honey[n - 1] + honey[n / 2]);
        num[3] = sum - (honey[0] + honey[n / 2]);
        num[4] = sum - (honey[0] + honey[1]);
        num[5] = sum - (honey[n - 2] + honey[n - 1]);
        

        for(int i = 0 ; i < num.length ; i++){
            System.out.println(num[i]);
            if(num[i] > max){
                max = num[i];
            }
        }
        System.out.println(max * 2);

    }
    
}
