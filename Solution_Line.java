import java.util.ArrayList;
import java.util.Arrays;

public class Solution_Line {
    public static void main(String[] args) {
        String[] strs = {"AABAAA", "BABABB", "BABBAAAB", "BABAAABAABBABBA"};
        String[] strs2 = {"AA", "BAB", "BAAAA", "ABBABB", "AABBBBABBAAAA"};
        String[] strs3 = {"AABAABAAB", "AABBBAABBB", "AABBBABBABABBBAAABBBABBBA"};
        Solution_Line solution = new Solution_Line();

        System.out.println(solution.solution(strs));
        System.out.println("----------------------------------------------");
        System.out.println(solution.solution(strs2));
        System.out.println("----------------------------------------------");
        System.out.println(solution.solution(strs3));
    }
    static int max_len = 0;
    static ArrayList<String> pattern = new ArrayList<>();
    static ArrayList<String> ab = new ArrayList<>(); // a or b로만 이루어져 있는 모든 문자열 조합을 담기 위한 리스트
    // 문제 : AAB~와 BAB~A를 마음대로 섞어서 만들 수 있는 모든 문자열
    public int solution(String[] strs) {
        int answer = 0;

         for (int i = 0 ; i < strs.length ; i++) {
            String str = strs[i];
            String[] str1 = str.split("BABB*A");
            StringBuilder sb = new StringBuilder();
            for (int a = 0 ; a < str1.length ; a++) {
                sb.append(str1[a]);
            }
            str1 = sb.toString().split("AABB*");
            boolean flag = true;
            for (String s : str1) {
                if (s.length() > 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) answer++;
         }
        return answer;
    }



}

// tc1 : ["AABAAA", "BABABB", "BABBAAAB", "BABAAABAABBABBA"]	2
// tc2 : ["AA", "BAB", "BAAAA", "ABBABB", "AABBBBABBAAAA"]	0
// tc3 : ["AABAABAAB", "AABBBAABBB", "AABBBABBABABBBAAABBBABBBA"]	3


// for (int i = 0 ; i < strs.length ; i++) {
//     String str = strs[i];
//     String[] str1 = str.split("BAB[B*[0-9]]A");
//     System.out.println(str + " " + Arrays.toString(str1));
    
// }

// 문제 설명
// 어떤 문자의 반복을 ~로 표시하기로 합니다. 예를 들어 A~는 A가 한 번 이상 반복되는 모든 문자열의 집합을 의미합니다. (ABC)~는 괄호 안에 있는 ABC로 구성된 문자열이 한 번 이상 반복되는 모든 문자열의 집합을 의미합니다.

// 예)
// B~ = {"B", "BB", "BBB", "BBBB", "B...B",....}

// (AB)~ = {"AB", "ABAB", "ABABAB", "AB....AB",....}

// (BAAB)~ = {"BAAB", "BAABBAAB", "BAABBAABBAAB...BAAB", ....}

// BA~BB = {"BABB", "BAABB", "BAAABB", "BAAAABB", "BAAA...ABB", ....}

// (BA~B)~ = {"BAB", "BAAB", "BAAAB", "BAAAAB", "BABBAAB", "BAABBABBAAABBAB", ....}

// 추가로 (A|B)는 A또는 B중에서 아무거나 하나만 선택해서 만든 문자열의 집합 {A,B}를 의미합니다.
// 예를 들면, (BAA|ABA)을 집합으로 표현하면 {BAA, ABA} 와 같습니다. 따라서 (A|B)~ 은 A와 B를 사용하여 만들 수 있는 모든 문자열의 집합을 의미합니다. 마찬가지로 (BAB|AA)~ 은 BAB와 AA을 마음대로 섞어서 만들 수 있는 모든 문자열의 집합을 의미합니다. 예를 들어 (BAB|AA)~ 에 해당하는 문자열을 나열하면 {"AA", "BAB", "AAAA", "BABAA", "AABAB", "AAAABAB", "BABBABBAB", "BABAAAAAA", "AABABBABAA",...} 입니다.

// 본 문제에서는 (AAB~|BAB~A)~ 패턴을 띄는 문자열이 몇 개인지 판별하려고 합니다.
// (AAB~|BAB~A)~ 패턴을 띄는 문자열은 {"AAB", "BABA", "AABBB", "BABBA", "BABABABA", "AABBBABBA", "BABBAAAB", "AABBAABAAB", "BABAAABAABBABBA",....}입니다. 즉, AAB~와 BAB~A를 마음대로 섞어서 만들 수 있는 모든 문자열을 판별하고자 합니다.

// 문자열이 담긴 배열 strs가 매개변수로 주어질 때, (AAB~|BAB~A)~ 패턴에 해당하는 문자열의 개수를 return 하도록 solution 함수를 완성해주세요.

// 제한사항
// strs는 길이 1 이상 200 이하인 배열입니다.
// strs의 원소는 길이가 1 이상 200 이하인 문자열입니다.
// strs의 원소는 A또는 B로만 이루어져 있습니다.
// 입출력 예
// strs	result
// ["AABAAA", "BABABB", "BABBAAAB", "BABAAABAABBABBA"]	2
// ["AA", "BAB", "BAAAA", "ABBABB", "AABBBBABBAAAA"]	0
// ["AABAABAAB", "AABBBAABBB", "AABBBABBABABBBAAABBBABBBA"]	3
// 입출력 예 설명
// 입출력 예 #1
// 두 개의 문자열 "BABBAAAB", "BABAAABAABBABBA"이 문제에서 정의한 패턴에 해당합니다. 따라서 2를 return 합니다.

// 입출력 예 #2
// 문제에서 정의한 패턴에 해당되는 문자열이 한 개도 없습니다. 따라서 0을 return 합니다.

// 입출력 예 #3
// 문자열 "AABAABAAB", "AABBBAABBB", "AABBBABBABABBBAAABBBABBBA" 모두 문제에서 정의한 패턴에 해당합니다. 따라서 3을 return 합니다.