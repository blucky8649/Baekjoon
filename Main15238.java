import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
public class Main15238 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int len = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int[] alp = new int[26];
        int max = Integer.MIN_VALUE;
        int result =0;
        for(int i = 0 ;i < len ; i++){
            alp[str.charAt(i)-'a'] ++;
        }
        
        for(int i = 0 ; i < alp.length ; i++){
            if(alp[i] > max){
                max = alp[i];
                result = i;
                
            }
        }
        System.out.println((char)(result + 'a') + " " + max);

    }
    
}
