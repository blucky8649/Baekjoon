import java.io.*;

public class Main1436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int num = 0;

        while (n > 0){
            num ++;
            if(String.valueOf(num).contains("666")){
                n--;
            }
        }
        System.out.println(num);
        
    }
}
