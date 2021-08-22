import java.io.*;

public class Main11729{
    public static int cnt=0;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        hanoi(n, 1, 3, 2);
        System.out.println(cnt);
        System.out.println(sb.toString());
    }
    public static void hanoi(int n, int from, int to, int other){
        if (n == 0){
            return;
        }
        hanoi(n-1, from, other, to);
        sb.append(from + " " + to + "\n");
        cnt++;
        hanoi(n-1, other, to, from);
    }
}