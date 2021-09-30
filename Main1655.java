import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main1655 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>(); 
        for(int i = 0 ; i < n ; i++){
            int x = Integer.parseInt(br.readLine());
            if(max.size() == min.size()){
                max.offer(x);
            }else{
                min.offer(x);
            }

            if(!max.isEmpty() && !min.isEmpty()){
                if(min.peek() < max.peek()){
                    int nn = min.poll();
                    int nx = max.poll();
                    max.offer(nn);
                    min.offer(nx);
                }
            }
            sb.append(max.peek()).append("\n");
        }
        System.out.print(sb.toString());
    }
    
}
