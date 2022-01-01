import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main5430 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < n ; i++) {
            boolean isErr = false;
            boolean reverse = false;
            String cmd = br.readLine();
            int len = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            arr = arr.replace("[", "");
            arr = arr.replace("]", "");
            String[] str = arr.split(",");
            
            Deque<Integer> q = new LinkedList<>();
            for (int j = 0 ; j < len ; j++) {
                q.offerLast(Integer.parseInt(str[j]));
            }

            for (int j = 0 ; j < cmd.length() ; j++) {
                char cr = cmd.charAt(j);
                switch (cr) {
                    case 'R': 
                        reverse = !reverse;
                        break;
                    case 'D': 
                        if (q.isEmpty()) {
                            isErr = true;
                        } else {
                            if (reverse) {
                                q.pollLast();
                            } else {
                                q.pollFirst();
                            }
                        }
                        break;
                }
            }
            if (isErr) {
                sb.append("error").append("\n");
            } else {
                sb.append("[");
                if (reverse) {
                    if (!q.isEmpty()) {
                        sb.append(String.valueOf(q.pollLast()));
                        while (!q.isEmpty()) {
                            sb.append(","+String.valueOf(q.pollLast()));
                        }
                    }
                    
                } else {
                    if (!q.isEmpty()) {
                        sb.append(String.valueOf(q.pollFirst()));
                        while (!q.isEmpty()) {
                            sb.append(","+String.valueOf(q.pollFirst()));
                        }
                    }
                    
                }
                sb.append("]").append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}
