import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Stack;



public class Main1918 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String infix =  br.readLine();
        StringBuilder sb = new StringBuilder();
        Stack<Character> op = new Stack<>();



        for(int i = 0 ; i < infix.length() ; i++){
            switch(infix.charAt(i)){
                case '(' :  op.push(infix.charAt(i)); break;
                case ')' :

                        while(!op.isEmpty() && op.peek() != '('){
                            sb.append(op.pop());
                        }
                        if(!op.isEmpty()) op.pop(); //'('연산자를 꺼내준다.
         
                     break;
                case '+' :
                case '*' :
                case '-' :
                case '/' : 
                    while(!op.isEmpty() && check(op.peek())>=check(infix.charAt(i))){
                         sb.append(op.pop());
                     }
                        
                    op.push(infix.charAt(i)); 
                    break;           
                default : sb.append(infix.charAt(i));  break;
            }
        }
        while(!op.isEmpty()) {
            sb.append(op.pop());
        }

        System.out.print(sb.toString());
    }
    public static int check(char ch){
        if(ch == '*' || ch == '/'){
            return 2;
        }else if (ch == '+' || ch == '-'){
            return 1;
        }else{
            return 0;
        }
    }
    
}
