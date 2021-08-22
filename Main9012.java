import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int top ;
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String[] arr;
        for(int i = 0 ; i < n ; i++){
            top = 0;
            arr = br.readLine().split("");
            for(int j = 0 ; j < arr.length ; j++){
                switch(arr[j]){
                    case "(" : top ++; break;
                    case ")" : top--; 
                    if(top < 0){
                        top = 10000;
                        break;
                    } break;
                }
            }
            if(top == 0){
                sb.append("YES");
            }else{
                sb.append("NO");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
        
    }
}
