import java.io.*;
import java.util.ArrayList;

public class Main2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] cnt = new int [8001];
        ArrayList<Integer> res = new ArrayList<>();
        
        
        double sum = 0.0; 
        double mid = Math.round(n / 2); 
        int max = 0; 


        for(int i = 0 ; i < n ; i++){
            int num = Integer.parseInt(br.readLine());
            
            cnt[num + 4000] ++;
        }
        
        


        for(int i = 0 ; i < cnt.length ; i++){
            if(cnt[i] > max) max =cnt[i];
            for(int j  = 0 ; j < cnt[i] ; j++){
                res.add(i - 4000);
                sum += (i-4000);
            }
        }
        int mode = 0;
        boolean flag = false;
        for(int i = 0; i < cnt.length;i++){
            if(cnt[i] == max){
                if(!flag){
                    flag = true;
                    mode = i - 4000;
                }else{
                    mode = i - 4000;
                    break;
                }
            }
        }
        System.out.println(Math.round(sum / n)); // 산술평균
        System.out.println(res.get((int)mid)); // 중간값
        System.out.println( mode); // 최빈값
        System.out.println( (res.get(n-1) - res.get(0))); // 범위
    }
}
