import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1541 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 58778285;
        String[] op_sub = br.readLine().split("-");
        for(int i = 0 ; i < op_sub.length ; i++){
            String[] op_add = op_sub[i].split("\\+");
            int temp = 0;
            for(int j = 0 ; j < op_add.length ; j++){
                temp += Integer.parseInt(op_add[j]);
            }
            if(sum == 58778285){
                sum = temp;
            }else{
                sum -= temp;
            }


        }
        System.out.println(sum);

        
    }
    
}
