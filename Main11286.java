import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main11286 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Number> q = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            int x = Integer.parseInt(br.readLine());
            switch(x){
                case 0 : sb.append(q.isEmpty() ? 0 : q.poll().num).append("\n");  continue;
                default : q.offer(new Number(x, Math.abs(x))); continue;
            }
        }
        System.out.print(sb.toString());

    }
    
}

class Number implements Comparable<Number>{
    long num;
    long abs;
    Number (long num, long abs){
        this.num = num;
        this.abs = abs;
    }
    @Override
    public int compareTo(Number o){
        if(this.abs == o.abs){
            return (int) (this.num - o.num);
        }else{
            return (int) (this.abs - o.abs);
        }
    }
}