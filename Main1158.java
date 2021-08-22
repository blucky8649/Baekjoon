import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main1158 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int term = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i <= n ; i++){
            queue.offer(i);
        }
        sb.append("<");
        while(!queue.isEmpty()){
            for(int i = 1 ; i <= term ; i++){
                if( i != term){
                    queue.offer(queue.remove());
                }else{
                    sb.append(queue.remove());
                }
            }
            if(queue.size() > 0){
                sb.append(", ");
            }
            
        }
        sb.append(">");
        System.out.println(sb.toString());

    }
    
}
