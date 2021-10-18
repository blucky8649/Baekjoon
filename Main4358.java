import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;


public class Main4358 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, Double> map = new TreeMap<>();
        int cnt = 0;
        while (true) {
            String str = br.readLine();
            
            if (str == null || str.length() == 0) break;
            cnt++;
            if(!map.containsKey(str)) {
                map.put(str, 1.0);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append(key + " " + String.format("%.4f", ((map.get(key) * 100) / cnt))+ "\n");
        }
        System.out.println(sb.toString());
    }
    
}
