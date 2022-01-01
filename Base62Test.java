
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Base62Test{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        long value = 0;
        for (int i = 0 ; i < str.length() ; i++) {
            char cr = str.charAt(i);
            value += (int)cr * (i+1);
        }
        System.out.println("VALUE : " + value);
        String encodedReservationNo = Base62.encodeToLong(value);
        System.out.println("ORIGINAL : " + value + ", ENCODE : " + encodedReservationNo + ", DECODE : " + Base62.decodeToLong(encodedReservationNo));
        
    }
}