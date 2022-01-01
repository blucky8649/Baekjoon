import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2525 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int p = Integer.parseInt(br.readLine());

        m += p;
        h += m / 60;
        m = m % 60;
        
        System.out.println((h >= 24 ? h - 24 : h) + " " + m);
    }
    
}
