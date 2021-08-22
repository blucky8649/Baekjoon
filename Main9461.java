import java.io.*;



public class Main9461 {
    public static long[] dp = new long[101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 2;
        dp[5] = 3;
        dp[6] = 4;
        dp[7] = 5;
        for(int i = 0 ; i < t ; i++){
            int n = Integer.parseInt(br.readLine());
           sb.append(TRI(n-1)).append("\n");
        }

        System.out.print(sb.toString());
    }
    public static long TRI(int n){
        
        if( dp[n] ==0){
            return dp[n] = TRI(n-5) + TRI(n-1);
        }
        return dp[n];
    }
}
