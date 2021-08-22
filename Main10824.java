import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

public class Main10824 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        long r1 = Long.parseLong(a[0] + a[1]);
       long r2 = Long.parseLong(a[2] + a[3]);

        System.out.println(r1 + r2);
    }
    
}
