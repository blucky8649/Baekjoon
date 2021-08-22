import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main6588 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int max=1000000;
        boolean flag = false;
        boolean[] isPrime = getPrime(max);
        while(true){
            flag = false;
            int num = Integer.parseInt(br.readLine());
            if(num == 0){
                break;
            }
            for(int i = 3 ; i < num ; i += 2){
                if(!isPrime[i] && !isPrime[num - i] && !flag){
                    sb.append(num + " = " + i + " + " + (num-i)).append("\n");
                    flag = true;
                    break;
                }
                
            }
            if(!flag){
                sb.append("Goldbach's conjecture is wrong.").append("\n");
            }
        }
        

            
    
        
        System.out.print(sb.toString());


    }
    public static boolean[] getPrime(int max){
        boolean[] prime = new boolean[max+1];
        prime[0]=prime[1] = true;

        for(int i = 2 ; i < Math.sqrt(max) ; i++){
            if(prime[i]) continue;
            for(int j = i*i ; j <max ; j += i){
                prime[j] = true;
            }
        }
        

        

        return prime;
    }
    
}
