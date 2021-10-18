import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main1764 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine() ," ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0 ; i < N ; i++) {
            String str = br.readLine();
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }

        for (int i = 0 ; i < M ; i++) {
            String str = br.readLine();
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (String key : map.keySet()) {
            if (map.get(key) == 2) {
                cnt++;
                sb.append(key).append("\n");
            }
        }
        System.out.println(cnt);
        System.out.print(sb.toString());
    }
    
}
