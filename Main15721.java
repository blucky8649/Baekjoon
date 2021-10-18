import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main15721 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int t = Integer.parseInt(br.readLine());
        int a = Integer.parseInt(br.readLine());

        int b = 0, d = 0, cnt = 2; // 뻔데기를 cnt 만큼 외치는 회차.
        while (true) {
            
            for (int i = 0 ; i < 4 ; i++) {
                if (i % 2 == 0) {
                    b ++; // 뻔
                    
                } else {
                    d++; // 데기
                }

                if ((b == t && a == 0) || (d == t && a == 1)) {
                    System.out.println((b + d - 1) % n);
                    return;
                }
                
            }
            // cnt 만큼 뻔 / 데기
            for (int i = 0 ; i < cnt ; i++) {
                b++;
                if ((b == t && a == 0)) {
                    System.out.println((b + d - 1) % n);
                    return;
                }
            }
            for (int i = 0 ; i < cnt ; i++) {
                d++;
                if ((d == t && a == 1)) {
                    System.out.println((b + d - 1) % n);
                    return;
                }
            }
            cnt++;
        }
    }
    
}
