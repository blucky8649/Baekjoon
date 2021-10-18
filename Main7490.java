import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;


public class Main7490 {
    static TreeSet<String> set = new TreeSet<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < t ; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] num = new int[n];

            for (int j = 1 ; j <= n ; j++) {
                num[j-1] = j; // 1, 2, 3 수열 만들기
            }
            DFS(1, num, n, "1");

        }
    }
    static void DFS(int depth, int[] num, int n, String sik) {
        if (depth >= n) {
            System.out.println(sik);
        }
        // 0 : +, 1:-, " " : 이어붙이기
        for (int i = 0 ; i < 3 ; i++) {
            System.out.println(num[depth]);
            switch (i) {
                case 0 : 
                    System.out.println(depth + " sadaaas");
                    DFS(depth + 1, num, n, sik + "+" + num[depth]+"");
                    break;
                case 1 : 
                    DFS(depth + 1, num, n, sik + "-" + num[depth]+"");
                    break;
                case 2 : 
                    DFS(depth + 1, num, n, sik + "" + num[depth]);
                    break;
            }
        }
    }
}
