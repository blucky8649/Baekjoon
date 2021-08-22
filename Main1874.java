import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


public class Main1874 {
    public static void main(String[] args) throws IOException{
        boolean tf = false;
        int inStack = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            int num = Integer.parseInt(br.readLine());
            if(stack.size() < num){
                for(int j = inStack ; j <= num ; j++, inStack++){
                    stack.push(j);
                    sb.append("+").append("\n");
                }
            }
            if(stack.peek() == num){
                sb.append("-").append("\n");

            }else{
                    tf = true;
            }
            
            

        }
        if(tf){
            System.out.print("NO");
        }else{
            System.out.print(sb.toString());
        }
        
        
    }
    
}
