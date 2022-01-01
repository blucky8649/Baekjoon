import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main14002 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int A = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] num = new int[A];
        ArrayList<Integer>[] arr = new ArrayList[A];
        for (int i = 0 ; i < arr.length ; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0 ; i < A ; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        for (int i = 0 ; i < A ; i++) {
            arr[i].add(num[i]);
            for (int j = 0 ; j < i ; j++) {
                if (num[j] < num[i]) {
                    if (arr[i].size() < arr[j].size() + 1) {
                        arr[i] = new ArrayList<>(arr[j]);
                        arr[i].add(num[i]);
                    }
                }
            }
        }
        for (int i = 0 ; i < arr.length ; i++) {
            max = Math.max(max, arr[i].size());
        }
        System.out.println(max);
        for (int i = 0 ; i < arr.length ; i++) {
            if (arr[i].size() == max) {
                for (int n : arr[i]) {
                    System.out.print(n + " ");
                }
                return;
            }
        }
    }
}
