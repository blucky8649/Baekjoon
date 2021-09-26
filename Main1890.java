import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1890 {
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       String s = br.readLine();
       s = s.replace("XXXX", "AAAA");
       s = s.replace("XX", "BB");

       System.out.println(s.contains("X") ? -1 : s);
    }
}
