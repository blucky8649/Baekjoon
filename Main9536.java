import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main9536 {
    public static void main(String[] args) throws IOException{
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < t ; i++) {
            String sound = br.readLine();
            HashSet<String> set = new HashSet<>();
            while (true) {
                String s = br.readLine();
                if (s.equals("what does the fox say?")) break;
                String[] arr = s.split(" ");
                set.add(arr[2]);
            }
            String arr[] = sound.split(" ");
            for (int j = 0 ; j < arr.length ; j++) {
                if (!set.contains(arr[j])) {
                    sb.append(arr[j] + " ");
                }
            }
            
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
