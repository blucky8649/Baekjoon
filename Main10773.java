import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main10773 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0 ; i < n ; i ++) {
            int num = Integer.parseInt(br.readLine());
            switch (num) {
                case 0 : stack.pop(); break;
                default : stack.push(num); break;
            }
        }
        int ans = 0;
        for (int num : stack) {
            ans += num;
        }
        System.out.print(ans);
    }
    
}
