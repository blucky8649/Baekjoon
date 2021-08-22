import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();
        String razer = br.readLine();
        int sum = 0;
        for(int i = 0 ; i<razer.length(); i++){
            switch(razer.charAt(i)){
                case '(' :
                    stack.push(razer.charAt(i)); 
                    break;
                case ')' :
                    if(razer.charAt(i-1) == '('){
                        stack.pop();
                        sum += stack.size();
                    } else{
                        stack.pop();
                        sum += 1;
                    }
                    break;
            }
        }
        System.out.println(sum);

    }
}
