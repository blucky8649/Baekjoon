import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main20546 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        int jun = n; // 준현 돈
        int jun_cnt = 0; // 준현 주식 수
        int sung = n; // 성민 돈
        int sung_cnt = 0; // 성민 주식 수

        int prev = 0; // 전날 주식기격
        int up = 0;
        int down = 0; 
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0 ; i < 14 ; i++) {
            int juga = Integer.parseInt(st.nextToken());
            if (jun >= juga) {
                //만약 준현이가 주식을 살 수 있으면 다삼.
                int cnt = jun / juga;
                jun_cnt += cnt;
                jun -= cnt * juga;
            }
            
            if (i > 0) {
                if (juga > prev) {
                    up++;
                    down = 0;
                } else if (juga < prev) {
                    down++;
                    up = 0;
                }
            }
            // 만약 주가가 3번이상 하락하면 성민이는 주식을 산다.
            if (down >= 3) {
                if (sung >= juga) {
                    //만약 성민이가 주식을 살 수 있으면 다삼.
                    sung_cnt += sung / juga;
                    int cnt = sung / juga;
                    sung -= cnt * juga;
                    
                }
            }
            // 주가가 3번 이상 오르면 주식을 매도한다. (단, 보유하고 있는 주식이 있어야함)
            if (up >= 3 && sung_cnt > 0) {
                sung += sung_cnt * juga;
                sung_cnt = 0; // 전량 매도
            }
            // 14일차에는 두 사람 다 주식을 전부 매도한다.
            if (i == 13) {
                if (jun_cnt > 0) {
                    jun += jun_cnt * juga;
                }

                if (sung_cnt > 0) {
                    sung += sung_cnt * juga;
                }
            }
            prev = juga;
        }
        if (jun < sung) {
            System.out.print("TIMING");
        } else if (jun > sung) {
            System.out.print("BNP");
        } else {
            System.out.print("SAMESAME");
        }
    }
    
}
