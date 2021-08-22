import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main17413 {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        
        Stack<Character> stack = new Stack<>();
 
        boolean checkTag = false;
        int z = 0;
        while(z < a.length){
            String str = a[z];
            for(int i = 0 ; i < str.length() ; i++){
                if (str.charAt(i) == '<') {
                    checkTag = true;
                    print(stack); 
                    sb.append(str.charAt(i));
                }else if(str.charAt(i) == '>'){
                    checkTag = false; 
                    sb.append(str.charAt(i));
                }else if(checkTag){
                    sb.append(str.charAt(i));
                }else{
                    stack.push(str.charAt(i));
                }
                  
                
                
            }
            print(stack);
            sb.append(" ");
            z++;
        }
        
        System.out.print(sb.toString());
    }
    public static void print(Stack<Character> stack){
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
    }
}
