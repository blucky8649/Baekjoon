import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main19598 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Meeting> q = new PriorityQueue<>();
        for(int i = 0 ; i < n ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            q.offer(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        PriorityQueue<Integer> room = new PriorityQueue<>();
        while(!q.isEmpty()){
            Meeting d = q.poll();

            if(!room.isEmpty() && d.start >= room.peek()){
                room.poll();
            }
            room.offer(d.end);
            
        }

        System.out.println(room.size());
        
    }
    
}
class Meeting implements Comparable<Meeting>{
    int start;
    int end;
    Meeting(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    public int compareTo(Meeting o){
        if(this.start == o.start){
            return this.end - o.end;
        }else{
            return this.start - o.start;
        }
    }

}