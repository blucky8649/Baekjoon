import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main21939 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeSet<Problem> set = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            set.add(new Problem(num, val));
            map.put(num, val);
        }

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            String cmd = st.nextToken();

            switch (cmd) {
                // 추가
                case "add" : 
                    int num = Integer.parseInt(st.nextToken());
                    int val = Integer.parseInt(st.nextToken());

                    set.add(new Problem(num, val));
                    map.put(num, val);
                    break;
                case "recommend" :
                    int idx = Integer.parseInt(st.nextToken());
                    if (idx == 1) {
                        sb.append(set.last().num).append("\n");
                    } else {
                        sb.append(set.first().num).append("\n");
                    }
                    break;
                case "solved" :
                    num = Integer.parseInt(st.nextToken());
                    set.remove(new Problem(num, map.get(num)));
                    map.remove(num);
                    break;
            }

        }
        System.out.print(sb.toString());

    }
    
}
class Problem implements Comparable<Problem>{
    int num;
    int val;
    Problem(int num, int val) {
        this.num = num;
        this.val = val;
    }
    @Override
    public int compareTo(Problem o) {
        if (this.val == o.val) {
            return this.num - o.num;
        }
        return this.val - o.val;
    }
}