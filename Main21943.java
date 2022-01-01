import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main21943 {
    static int[] num;
    static int n, plus = 0, mul = 0, ans = 0;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        num = new int[n];
        isVisited = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < n ; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        plus = Integer.parseInt(st.nextToken());
        mul = Integer.parseInt(st.nextToken());
        for (int i = 0 ; i < n ; i++) {
            isVisited[i] = true;
            DFS(1, num[i], String.valueOf(num[i]));
            isVisited[i] = false;
        }
        System.out.println(ans);
    }
    static void DFS(int k, int sum, String oper) {
        if (k == n) {
            System.out.println(oper + "=" + sum);
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0 ; i < n ; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                // 더하는 경우
                if (plus > 0) {
                    // 1. 괄호를 씌우지 않는 경우
                    plus--;
                    DFS(k + 1, sum + num[i], oper + "+" + num[i]);
                    plus++;
                }
                // 곱하는 경우
                if (mul > 0) {
                    mul--;
                    DFS(k + 1, sum * num[i], oper + "*" + num[i]);
                    mul++;
                }
                isVisited[i] = false;
            }
        }
        
    }
    static int calc(char op, int n1, int n2) {
        switch (op) {
            case '*' : return n1 * n2;
            case '+' : return n1 + n2;
        }
        return 1;
    }
}
