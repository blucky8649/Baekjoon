import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main11000 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Time> q = new PriorityQueue<>();
        PriorityQueue<Integer> room = new PriorityQueue<>();
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            q.offer(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        while(!q.isEmpty()){
            Time d = q.poll();
            if(!room.isEmpty() && d.start >= room.peek()){
                room.poll();
            }
            room.offer(d.end);
        }

        System.out.println(room.size());

    }
    
}
class Time implements Comparable<Time>{
    int start;
    int end;
    Time(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    public int compareTo(Time o){
        if(this.start == o.start){
            return this.end - o.end;
        }else{
            return this.start - o.start;
        }
        
    }
}