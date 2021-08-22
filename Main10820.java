import java.io.IOException;
import java.util.Scanner;



public class Main10820 {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int lower, upper, num, sp;
        
        while(sc.hasNextLine()){
            lower = upper = num = sp = 0;
            String str = sc.nextLine();
            
            for(int i = 0 ;i< str.length(); i++){
                
                if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){
                    lower++;
                }else if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
                    upper++;
                }else if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                    num++;
                }else{
                    sp++;
                }

                
            }
            
            sb.append(lower + " " + upper + " " + num + " " + sp).append("\n");
            
        }
        sc.close();
        
        

    }
    
}
