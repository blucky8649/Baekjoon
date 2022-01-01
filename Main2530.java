import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2530 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine() , " ");

        int hour = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());
        int sec = Integer.parseInt(st.nextToken());

        int n = Integer.parseInt(br.readLine());

        min += n / 60;
        n %= 60;
        sec += n;
        
        min += sec / 60;
        sec %= 60;
        hour += min / 60;
        min %= 60;
        hour %= 24;
        
        System.out.println(hour + " " + min + " " + sec);
    }
}
