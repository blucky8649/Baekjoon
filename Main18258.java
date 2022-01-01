import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main18258 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> arr = new LinkedList<>();
        StringBuilder sb= new StringBuilder();
        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String cmd = st.nextToken();
            switch (cmd) {
                case "push" : 
                    arr.add(Integer.parseInt(st.nextToken()));
                    break;
                case "pop" : 
                    if (arr.isEmpty()) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(arr.poll()).append("\n");
                    }
                    break;
                case "front" : 
                    sb.append(arr.isEmpty() ? -1 : arr.getFirst()).append("\n");
                    break;
                case "back" : 
                    sb.append(arr.isEmpty() ? -1 : arr.getLast()).append("\n");
                    break;
                case "empty" : 
                    sb.append(arr.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "size" : 
                    sb.append(arr.size()).append("\n");
                    break;
            }
        }
        System.out.print(sb.toString());
    }
    
}
