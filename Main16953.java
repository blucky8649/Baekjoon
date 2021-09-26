import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main16953 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String n = st.nextToken();
        String m = st.nextToken();
        int cnt = 1;
        
        while(Long.parseLong(m) > Long.parseLong(n)){
            if (m.charAt(m.length()-1) =='1') {
                m = (Long.parseLong(m) / 10) +"";
            } else if(Long.parseLong(m) % 2 == 0) {
                m = (Long.parseLong(m) / 2)+"";
            } else{
                break;
            }
            cnt++;
            if(m.equals(n)){
                System.out.println(cnt);
                return;
            }
        }
        
        System.out.println(-1);
        
    }
    
}
