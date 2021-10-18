import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9935 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String boomStr = br.readLine();
        char[] cr = new char[str.length()];
        int index = 0;
        for (int i = 0 ; i < str.length() ; i++) {
            cr[index] = str.charAt(i);
            if (check(index, cr, boomStr)) index -= boomStr.length();
            index++;
        }
        String answer = String.valueOf(cr, 0, index);
        System.out.println(answer.length() == 0 ? "FRULA" : answer);
    }
    static boolean check (int index, char[] cr, String bomb) {
        if (index < bomb.length() - 1) return false;

        for (int i = 0 ; i < bomb.length() ; i++) {
            if (bomb.charAt(i) != cr[index - bomb.length() + 1 + i]) return false;
        }
        return true;
    }
    
}
