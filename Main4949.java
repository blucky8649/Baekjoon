import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main4949 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            Stack<Character> stack = new Stack<>();
            String str = br.readLine();
            if (str.equals(".")) break;

            for (int i = 0 ; i < str.length() ; i++) {
                char cr = str.charAt(i);
                switch (cr) {
                    case '(' : stack.push(cr); break;
                    case ')' : 
                        if (stack.isEmpty()) {
                            stack.push(cr);
                        } else if (stack.peek() == '(') {
                            stack.pop();
                        }else {
                            stack.push(cr);
                        }
                        break;
                    case '[' : stack.push(cr); break;
                    case ']' :
                        if (stack.isEmpty()) {
                            stack.push(cr);
                        } else if (stack.peek() == '[') {
                            stack.pop();
                        } else {
                            stack.push(cr);
                        }
                        break;
                }
            }
            if (stack.isEmpty()) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }
    
}
