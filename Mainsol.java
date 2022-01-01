import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Mainsol {
    static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        map = new HashMap<>();
        System.out.println(Arrays.toString(solution("KAKAO")));
        map = new HashMap<>();
        System.out.println(Arrays.toString(solution("TOBEORNOTTOBEORTOBEORNOT")));
        map = new HashMap<>();
        System.out.println(Arrays.toString(solution("ABABABABABABABAB")));
    }
    public static int[] solution(String msg) {
        ArrayList<Integer> arr = new ArrayList<>();
        init();
        String next = "";
        int end_idx = 0;
        String cur = "";
        for (int i = 0 ; i < msg.length() ;) {
            cur = String.valueOf(msg.charAt(i));
            end_idx = i == msg.length() - 1 ? i : i + 1;
            next = String.valueOf(msg.charAt(end_idx));
            while (map.containsKey(cur + next)) {
                if (i >= msg.length() - 1) break;
                cur += String.valueOf(msg.charAt(++i));
                if (end_idx >= msg.length() - 1) break;
                next = String.valueOf(msg.charAt(++end_idx));
            }
            arr.add(map.get(cur));
            if (i == end_idx) break;
            i = end_idx;
            map.put(cur + next, map.size() + 1);
        }
        int[] answer = new int[arr.size()];
        for (int i = 0 ; i < arr.size() ; i++) {
            answer[i] = arr.get(i);
        }
        return answer;
    }
    static void init() {
        for (int i = 1 ; i <= 26 ; i++) {
            map.put(String.valueOf((char)('A' + (i-1))), i);
        }
    }
}