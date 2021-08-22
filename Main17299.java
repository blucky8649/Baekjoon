import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main17299 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        int[] freQ = new int[1000001];
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int max=0;
        for(int i = 0 ; i < n ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            max = arr[i] > max ? arr[i] : max;
            freQ[arr[i]]++;
        }

        for(int i = 0 ; i < n ; i++){
            
            while(!stack.isEmpty() && freQ[arr[stack.peek()]] < freQ[arr[i]]){
         
                arr[stack.pop()] = arr[i];
            }
            stack.push(i);
        }
        
        while( !stack.isEmpty()){
            arr[stack.pop()] = -1;
        }
        
        
        for(int i = 0 ; i < n ; i++){
            sb.append(arr[i]).append(" ");
        }
        

        System.out.println(sb);
    }
    
}
