import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main16637 {
    static int ans = 0;
    static ArrayList<Character> op = new ArrayList<>();
    static ArrayList<Integer> num = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String sik = br.readLine();
        for (int i = 0 ; i < n ; i++) {
            char cr = sik.charAt(i);
            if (cr == '*' || cr == '-' || cr == '+') {
                op.add(cr);
            } else {
                num.add(Character.getNumericValue(cr));
            }
        }

        DFS(0, num.get(0));
        System.out.println(ans);
    }
    static void DFS(int idx, int sum) {
        if (idx >= op.size()) {
            ans = Math.max(ans, sum);
            return;
        }

        // 1. 괄호 안치고 계산하는 경우
        int unbrace = calc (idx, sum, num.get(idx + 1));
        DFS(idx + 1, unbrace);
        // 2. 괄호 치고 계산하는 경우
        if (idx + 1 < op.size()) { // OutOfIndex Exception 방지
            // 뒤에 두 수를 미리 계산하고 앞에 계산된 result이랑 다시 계산한다.
            int brace = calc(idx + 1, num.get(idx + 1), num.get(idx + 2));
            int result = calc(idx, sum, brace);
            DFS(idx + 2, result);
        }

    }
    static int calc(int op_idx, int n1, int n2) {
        int sum = 0;
        switch (op.get(op_idx)) {
            case '*' : sum = n1 * n2; break;
            case '+' : sum = n1 + n2; break;
            case '-' : sum = n1 - n2; break;
        }
        return sum;
    }
}
