import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main_Ebay2 {
    static ArrayList<String> arr;
    public static void main(String[] args) throws IOException{
        arr= new ArrayList<>();
        System.out.println(solution(new int[] {1, 3, 2}, 3));
        System.out.println("@@@@@@@@@@");
        arr= new ArrayList<>();
        System.out.println(solution(new int[] {4, 2, 2, 1, 4}, 1));
        System.out.println("@@@@@@@@@@");
        arr= new ArrayList<>();
        System.out.println(solution(new int[] {5, 7, 2, 4, 9}, 20));
    }
    static String solution(int[] stones, int k) {
        String answer = "";

        DFS(0, "", stones, k);
        Collections.sort(arr);
        return arr.size() > 0 ?  arr.get(arr.size() - 1) : "-1";
    }
    static void DFS(int depth, String routes, int[] stones, int k) {
        if (check(stones, k)) {
            System.out.println(Arrays.toString(stones) + " "+routes);
            arr.add(routes);
            return;
        }

        if (depth == stones.length) {
            return;
        }
        
        for (int i = 0 ; i < stones.length ; i++) {
            int clone[] = stones.clone();
            for (int a = 0 ; a < stones.length ; a++) {
                if (a == i) {
                    stones[a]++;
                } else {
                    if (stones[a] == 0) {
                        stones = clone.clone();
                        continue;
                    }
                    stones[a]--;;

                }
            }
            DFS(depth + 1, routes + i, stones, k);
            stones = clone.clone();
        }
    }
    static boolean check(int[] stones, int k) {
        int cnt = 0;
        for (int i = 0 ; i < stones.length ; i++) {
            if (stones[i] != 0 && stones[i] != k) return false;
            if (stones[i] > 0) cnt++;
            if (cnt > 1) return false;
        }
        return true;
    }
}
