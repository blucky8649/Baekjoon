import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_CF {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        

        // Math.abs(49 - cx) + Math.abs(3 - cy) = Math.abs(0 - cx) + Math.abs(0 - cy);
        for (int z = 0 ; z < t ; z++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            calc(x, y);
        }
        
        

    }
    static void calc(int x, int y) {
        for (int i = 0 ; i <= 50 ; i++) {
            for (int j = 0 ; j <= 50 ; j++) {
                if (Math.abs(x - j) + Math.abs(y - i) == Math.abs(0 - j) + Math.abs(0 - i)) {
                    System.out.println(j + " " + i);
                    return;
                }
            }
        }
        System.out.println("-1 -1");
    }
}
