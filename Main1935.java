import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main1935 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String postfix = br.readLine(); 
        Stack<Double> num = new Stack<>();
        double[] arr = new double[n+1];
        for(int i = 0 ; i < n ; i++){
            arr[i] = Double.parseDouble(br.readLine());
        }

        for(int i = 0 ; i < postfix.length() ; i++){
            switch(postfix.charAt(i)){
                case '+' :
                case '*' :
                case '-' : 
                case '/' : cal(num, num.pop(), num.pop(), postfix.charAt(i)); break;
                default : num.push(arr[(int)postfix.charAt(i) - 'A']); break;
                
            }
        }
        System.out.printf("%.2f", num.pop());
    }
    public static void cal(Stack<Double> stack, double a, double b, char op) {
        switch(op) {
        case '+':
            stack.push(b + a);
            break;
        case '-':
            stack.push(b - a);
            break;
        case '*':
            stack.push(b * a);
            break;
        case '/':
            stack.push(b / a);
            break;
        }

    }
}
