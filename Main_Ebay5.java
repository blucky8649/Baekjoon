import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_Ebay5 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(new int[][] {{4, 5}, {4,3}, {4, 2}, {1, 6}, {7, 4}, {7, 1}}));
        System.out.println(solution(new int[][] {{3, 5}, {3, 2}, {6, 3}, {6, 1}, {4, 6}}));
    }
    static int solution(int[][] links) {
        int answer = 0;
        int max = 0;
        for (int i = 0 ; i < links.length ; i++) {
            max = Math.max(links[i][0], links[i][1]);
        }
        int[] parents = new int[max + 1];
        Arrays.fill(parents, -1);
        ArrayList<Integer>[] list = new ArrayList[max + 1];
        for (int i = 0 ; i <= max ; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0 ; i < links.length ; i++) {
            int start = links[i][0];
            int end = links[i][1];

            list[start].add(end);
            parents[end] = start;
        }
        System.out.println(Arrays.toString(parents));

        for (int i = 0 ; i < parents.length ; i++) {
            if (parents[i] == -1) {
                
            }
        }
        return answer;
    }
}
