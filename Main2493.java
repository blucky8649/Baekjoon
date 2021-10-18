import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Tower {
    int num;
    int val;
    Tower (int num, int val) {
        this.num = num;
        this.val = val;
    }
}

public class Main2493 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Stack<Tower> stack = new Stack<>();
        int[] ans = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        for (int i = 1 ; i <= n ; i++) {
            int num = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty()) {
                if (stack.peek().val < num) {
                    stack.pop();
                    
                }else {
                    ans[i] = stack.peek().num;
                    break;
                }
            }
            
            if(stack.isEmpty()) {
                ans[i] = 0;
            }
            stack.push(new Tower(i, num));
        }
        

        for (int i = 1 ; i <= n ; i++) {
            sb.append(ans[i] + " ");
        }

        System.out.println(sb.toString());
    }
    
}
