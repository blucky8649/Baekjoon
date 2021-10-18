import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main1620 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<Integer, String> mapNum = new HashMap<>();
        HashMap<String, Integer> mapName = new HashMap<>();
        
        for (int i = 1 ; i <= n ; i++) {
            String str = br.readLine();
            mapNum.put(i, str);
            mapName.put(str, i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < m ; i++) {
            String str = br.readLine();
            if (str.charAt(0) >= 'A' && str.charAt(0) <= 'Z') {
                sb.append(mapName.get(str));
            } else {
                sb.append(mapNum.get(Integer.parseInt(str)));
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
    
}
