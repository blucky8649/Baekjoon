import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2075 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < n ; j++){
                int num = Integer.parseInt(st.nextToken());
                if(min.size() < n - 1){
                    min.offer(num);
                }else{
                    max.offer(num);
                    if(min.isEmpty())continue;
                    if(min.peek() < max.peek()){
                        int nn = min.poll();
                        int nx = max.poll();

                        min.offer(nx);
                        max.offer(nn);
                    }
                }
            }
        }

        System.out.println(max.peek());
    }
    
}
