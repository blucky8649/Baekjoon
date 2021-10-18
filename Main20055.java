import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main20055 {
    static int[] cv;
    static int n, k;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        cv = new int[n*2];
        int[] check = new int[n*2]; // 벨트위에 로봇이 있는지 여부
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < n * 2 ; i++) {
            cv[i] = Integer.parseInt(st.nextToken());
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int i = 0;
        while (moveable()) {
            // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            int tmp = cv[cv.length - 1]; // 마지막칸에 있는 내구도 정보
            int tmp2 = check[check.length - 1]; // 마지막 칸 컨베이어 탑승 여부
            
            for (int a = cv.length-1 ; a > 0 ; a--) {
                cv[a] = cv[a-1];
                check[a] = check[a-1];
            }
            
            cv[0] = tmp;
            check[0] = tmp2;

            for (int a = 0 ; a < check.length ; a++) {
                if (check[a] > 0) {
                    map.put(check[a], a);
                }
            }
            if (check[n - 1] > 0) {
                map.remove(check[n - 1]);
                check[n - 1] = 0;
            }
            // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동한다.
            if (map.size() > 0) {
                for (int key : map.keySet()) {
                    if (cv[(map.get(key) + 1) % cv.length] > 0 && check[(map.get(key) + 1) % cv.length] == 0) {
                        // 2-1 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아있을 시 로봇 이동시킴
                        check[(map.get(key) + 1) % cv.length] = key;
                        check[map.get(key)] = 0;
                        cv[(map.get(key) + 1) % cv.length]--; // 내구도 감소\
                        map.put(key, (map.get(key) + 1) % cv.length);
                    }
                }
            }
            if (check[n - 1] > 0) {
                map.remove(check[n - 1]);
                check[n - 1] = 0;
            }
            // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            if (cv[0] > 0 && check[0] == 0) {
                map.put(i+1, 0);
                check[0] = i+1;
                cv[0]--;
            }
            i++;
        }
        System.out.println(i);
    }
    static boolean moveable() {
        int cnt = 0;
        for (int i = 0 ; i < cv.length ; i++) {
            if (cv[i] == 0) cnt++;
        }

        if (cnt >= k) return false;
        return true;
    }
    
}