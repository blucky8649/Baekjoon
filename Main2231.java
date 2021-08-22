import java.io.*;

public class Main2231{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int sum=0;
        int res = 0;
        int num=0;
        for(int i = n ; i > 0 ; i--){
            num = sum = i;
            while(num >0){
                sum += num % 10;
                num /= 10;
                
            }
            
            if(sum == n){
                res = i;
            }

        }
        System.out.println(res);
    }
}