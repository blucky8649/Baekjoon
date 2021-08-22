import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main18870 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        int[] num1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < n ; i++){
            num1[i] = Integer.parseInt(st.nextToken());

        }

        int[] num2 = num1.clone();
        Arrays.sort(num2);
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (map.get(num2[i]) == null) { //아직 압축 안된 숫자인 경우
                map.put(num2[i], cnt++);
            }
        }

        for (int i = 0; i < n; i++) {
            sb.append(map.get(num1[i])).append(" ");
        }
        System.out.println(sb.toString());
    }
}
