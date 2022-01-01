import java.util.ArrayList;
import java.util.Collections;

public class MainS {
    public static void main(String[] args) {
        System.out.println(solution("aaaabbbbbaaabababababababbabaabbaaaaabbbbbababababababbbbbbbbbbbaaaaaaaaaaaabaabaabbbaaaaaaaa"));
    }
    public static int solution(String S) {
        // write your code in Java SE 11
        int n = 1;
        ArrayList<Integer> arr = new ArrayList<>();
        char prev = S.charAt(0);
        for (int i = 1 ; i < S.length() ; i++) {
            char cr = S.charAt(i);
            if (cr == prev) {
                n++;
            } else {
                prev = cr;
                arr.add(n);
                n = 1;
            }
        }
        arr.add(n);
        System.out.println(arr);
        int max = Collections.max(arr);
        int ans = 0;
        for (int num : arr) {
            ans += max - num;
        }
        return ans;
    }
}
