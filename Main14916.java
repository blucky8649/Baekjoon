import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main14916 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int money = Integer.parseInt(br.readLine());
        int cnt = 0;
        if (money == 1 || money == 3){
            System.out.println(-1);
            return;
        }
        //2원, 5원으로만 구성된
        while(money % 5 != 0){
            money = money -2;
            cnt++;
        }
        cnt += money / 5;
        money %= 5;

        System.out.println(cnt);
    }
    
}
