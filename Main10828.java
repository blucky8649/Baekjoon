import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main10828 {
    public static int[] stack = new int[10000];
    public static int top = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            switch(st.nextToken()){
                case "push" : push(Integer.parseInt(st.nextToken())); break;
                case "pop" : sb.append(pop()).append("\n"); break;
                case "size" : sb.append(size()).append("\n"); break;
                case "empty" : sb.append(empty()).append("\n"); break;
                case "top" : sb.append(top()).append("\n"); break;
            }
        }
        System.out.print(sb.toString());
        


    }
    public static void push(int n){
        stack[top] = n;
        top ++;
    }

    public static int pop(){
        if(top == 0){
            return -1;
        }
        else{
            top--;
            return stack[top];
        }
    }
    public static int size(){
       
        return top;
    }

    public static int empty(){
        if(top == 0){
            return 1;
        }
        return 0;
    }
    public static int top(){
        if(top ==0){
            return -1;
        }
        return stack[top-1];
    }

}
