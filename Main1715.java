import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main1715 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int answer = 0;
        for(int i = 0 ; i < n ; i++){
            q.offer(Integer.parseInt(br.readLine()));
        }
        while(!q.isEmpty() && q.size() > 1){
            int num = q.poll() + q.poll();
            q.offer(num);
            answer += num;
            if(q.size() == 1) break;
        }
        System.out.println(answer);
        
    }
    
}
