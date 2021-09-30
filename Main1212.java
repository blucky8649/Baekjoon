import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main1212 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n.length() ; i++){
            String s = Integer.toBinaryString(n.charAt(i) - '0');
            if(i != 0){
                if(s.length() == 1){
                    sb.append("00"+s);
                }else if(s.length() == 2){
                    sb.append("0" + s);
                }else{
                    sb.append(s);
                }
            }else{
                sb.append(s);
            }
            
        }
        System.out.println(sb.toString());
    }
    
}
