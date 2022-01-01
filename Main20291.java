import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main20291 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TreeMap<String, Integer> map = new TreeMap<>();
        for (int i = 0 ; i < n ; i++) {
            String file = br.readLine();
            int idx = file.indexOf(".") + 1;
            String s = file.substring(idx);
            if (!map.containsKey(s)) {
                map.put(s, 1);    
            } else {
                map.put(s, map.get(s) + 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : map.keySet()) {
            sb.append(s + " " + map.get(s)).append("\n");
        }
        System.out.print(sb.toString());
    }
}
