import java.io.*;

public class Main1259 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true){
            StringBuilder sb = new StringBuilder();
            String n = br.readLine();
            if(n.equals("0")){
                break;
            }
            String arr[] = n.split("");
            for(int i = n.length()-1 ; i >= 0; i--){
                sb.append(arr[i]);                
            }

            if(sb.toString().equals(n)){
                System.out.println(sb.toString()+ "yes");
            }else{
                System.out.println(sb.toString()+ "no");
            }

            
        }
    }
    
}
