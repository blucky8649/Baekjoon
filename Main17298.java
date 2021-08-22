import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main17298 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
  
        int[] arr = new int[n];
        for(int i = 0 ; i<n ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0 ; i < n ; i++){
            
                
         
                while(!stack.empty() && arr[stack.peek()] < arr[i]) {
                    arr[stack.pop()] = arr[i];
                }
                stack.push(i);
        }
        while(!stack.empty()) {
            arr[stack.pop()] = -1;
        }

        for(int i = 0 ; i < arr.length ; i++){
            sb.append(arr[i]).append(" ");
        }
        System.out.print(sb.toString());
        
    }
    
}
