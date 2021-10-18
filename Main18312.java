import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main18312 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int ans = 0;
        for (int i = 0 ; i < 60*60 * (N + 1) ; i++) {
            
            int hour = i / (60*60);
            String hourS = (hour+"").length() == 1 ? "0"+hour : hour+"";
            int min = (i % (60*60)) / 60;
            String mins = (min+"").length() == 1 ? "0"+min : min+"";
            int sec = (i % (60*60)) % 60;
            String secs = (sec+"").length() == 1 ? "0"+sec : sec+"";
            String str = hourS +""+ mins+""+ secs;
            if (str.contains(K+"")){
                ans++;
            }
        }

        System.out.println(ans);
    }
    
}
