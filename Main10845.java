import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main10845 {
    public static Queue<Integer> queue = new LinkedList<>();
    public static StringBuilder sb = new StringBuilder();
    public static int back = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            switch(st.nextToken()){
                case "push" : 
                            back  = Integer.parseInt(st.nextToken());
                            queue.offer(back); break;
                case "pop" : pop(); break;
                case "size" : sb.append(queue.size()).append("\n"); break;
                case "empty" : empty(); break;
                case "front" : sb.append(queue.size() == 0 ? -1 : queue.peek()).append("\n"); break;
                case "back" : back(); break;
                default : break;
            }
        }
        System.out.print(sb.toString());
    }
    public static void pop(){
        String pop = String.valueOf(queue.poll()); 
        if(pop.equals("null")){
            sb.append("-1").append("\n");
        }else{
            sb.append(pop).append("\n");
        }
    }
    public static void empty(){
        if(queue.size() == 0){
            sb.append("1").append("\n");
        }else{
            sb.append("0").append("\n");
        }
    }
    public static void back(){
        if(queue.size() == 0){
            back = -1;
            sb.append(back).append("\n");
        }else{
            sb.append(back).append("\n");
        }
    }
}
