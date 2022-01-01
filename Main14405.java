import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main14405 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        while (str.contains("pi")) {
            str = str.replaceFirst("pi", "!");
        }
        while (str.contains("ka")) {
            str = str.replaceFirst("ka", "!");
        }
        while (str.contains("chu")) {
            str = str.replaceFirst("chu", "!");
        }
        str = str.replace("!", "");
        System.out.println(str.length() == 0 ? "YES" : "NO");
    }
}
