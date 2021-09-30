import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main11279 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            int x = Integer.parseInt(br.readLine());
            switch(x){
                case 0 : sb.append(q.isEmpty() ? 0 : q.poll()).append("\n"); continue;
                default : q.offer(x); continue;
            }
        }
        System.out.print(sb.toString());
    }
    
}
