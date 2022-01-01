import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main10866 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> deque = new ArrayDeque<>();
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i< n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            switch(st.nextToken()){
                case "push_front": deque.offerFirst(Integer.parseInt( st.nextToken())); break;
                case "push_back": deque.offerLast(Integer.parseInt(st.nextToken())); break;
                case "pop_front": sb.append(deque.peekFirst() == null ? -1 : deque.removeFirst()).append("\n"); break;
                case "pop_back": sb.append(deque.peekLast() == null ? -1 : deque.removeLast()).append("\n"); break;
                case "size": sb.append(deque.size()).append("\n"); break;
                case "empty": sb.append(deque.isEmpty()?1 : 0).append("\n"); break;
                case "front": sb.append(deque.peekFirst() == null ? -1 : deque.peekFirst()).append("\n"); break;
                case "back": sb.append(deque.peekLast() == null ? -1 : deque.peekLast()).append("\n"); break;

            }
        }
        
        System.out.print(sb.toString());
        
        
    }
}
