import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main211126_3 {
    static int n;
    public static BigInteger max = BigInteger.valueOf(Integer.MIN_VALUE), m = BigInteger.valueOf(Integer.MIN_VALUE);
    public static BigInteger mm;
    static BigInteger[] arr, ans;
    static ArrayList<BigInteger> list = new ArrayList<>();
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int midx = 0;
        PriorityQueue<BI> q = new PriorityQueue<>();
        n = Integer.parseInt(br.readLine());
        arr = new BigInteger[n];
        ans = new BigInteger[n];
        isVisited = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < n ; i++) {
            arr[i] = BigInteger.valueOf(Integer.parseInt(st.nextToken()));
            if (m.compareTo(arr[i]) < 0) {
                m = arr[i];
                midx = i;
            }
        }

        ans[0] = m;
        isVisited[midx] = true;
        DFS(1, ans, ans[0]);
        System.out.println(max);
    }
    static void DFS(int k, BigInteger[] a, BigInteger sum) {
        if (k == n) {

            if (max.compareTo(sum) < 0) {
                max = sum;
            }
            return;
        }

        for (int i = 0 ; i < n ; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                a[k] = a[k -1].gcd(arr[i]);
                DFS(k + 1, a, sum.add(a[k]));
                isVisited[i] = false;
            }
        }
    }
}
class BI implements Comparable<BI>{
    BigInteger num;
    BigInteger g;
    BI(BigInteger num, BigInteger g) {
        this.num = num;
        this.g = Main211126_3.mm.gcd(g);
    }
    @Override
    public int compareTo(BI o) {
        return o.g.subtract(this.g).intValue();
    }
}