import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2696 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int i = 0 ; i < t ; i++) {
            PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> min = new PriorityQueue<>();
            int n = Integer.parseInt(br.readLine());
            Queue<Integer> q= new LinkedList<>();
            

            while (n != 0) {
                if (n <= 10) {
                    st = new StringTokenizer(br.readLine(), " ");
                    int a = n;
                    for (int j = 1 ; j <= n ; j++) {
                        if (j % 2 == 1) {
                            max.offer(Integer.parseInt(st.nextToken()));
                        } else {
                            min.offer(Integer.parseInt(st.nextToken()));
                        }
        
                        if (!min.isEmpty() && max.peek() > min.peek()) {
                            int tmp = max.poll();
                            max.offer(min.poll());
                            min.offer(tmp);
                        }

                        if (j % 2 == 1) {
                            q.offer(max.peek());
                        }
                    }
                    n -= a;
                } else {
                    st = new StringTokenizer(br.readLine(), " ");
                    for (int j = 1 ; j <= 10 ; j++) {
                        if (j % 2 == 1) {
                            max.offer(Integer.parseInt(st.nextToken()));
                        } else {
                            min.offer(Integer.parseInt(st.nextToken()));
                        }
        
                        if (!min.isEmpty() && max.peek() > min.peek()) {
                            int tmp = max.poll();
                            max.offer(min.poll());
                            min.offer(tmp);
                        }
                        if (j % 2 == 1) {
                            q.offer(max.peek());
                        }
                    }
                    n -= 10;
                }
            }
            if (i != 0) {
                sb.append("\n");
            }
            
            sb.append(q.size()).append("\n");
            while(!q.isEmpty()) {
                if (q.size() <= 10) {
                    while(!q.isEmpty()) {
                        sb.append(q.poll() + " ");
                    }
                } else {
                    for(int a = 0 ; a < 10 ; a++) {
                        sb.append(q.poll() + " ");
                    }
                    sb.append('\n');
                }
            }
        }
        System.out.println(sb.toString());
    }
    
}
