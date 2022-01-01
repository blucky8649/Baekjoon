import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1107 {
    static int n, ans = 100;
    static boolean[] isVisited, check;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        isVisited = new boolean[String.valueOf(n).length() + 1];
        check = new boolean[10];
        if (m > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0 ; i < m ; i++) {
                check[Integer.parseInt(st.nextToken())] = true; // 고장난 버튼은 빼준다.
            }
        }
        
        ans = Math.abs(n - ans); // 만약 버튼을 안누르고 채널 위아래만 까딱까딱해서 가능한 경우
        DFS(0, "");
        System.out.println(ans);
    }
    static void DFS(int k, String str) {
        if (k >= 1 ) {
            int cnt = str.length();
            cnt += Math.abs(Integer.parseInt(str) - n);
            ans = Math.min(cnt, ans);

            if (k == isVisited.length) return;
        }

        for (int i = 0 ; i < 10 ; i++) {
            if (!isVisited[k] && !check[i]) {
                isVisited[k] = true;
                DFS(k + 1, str + i);
                isVisited[k] = false;
            }
        }


    }
}
