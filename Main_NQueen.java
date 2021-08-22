import java.io.*;

public class Main_NQueen {
    public static int N;
    public static int cnt = 0;
    public static boolean[] isUsed1  = new boolean[40];
    public static boolean[] isUsed2  = new boolean[40];
    public static boolean[] isUsed3  = new boolean[40];
    
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Queen(0);
        System.out.println(cnt);


        
    }
    public static void Queen(int cur){
        if  (cur == N){
            cnt++;
            return;
        }


        for(int i = 0 ; i < N ; i++){
            if(isUsed1[i] || isUsed2[i+cur] || isUsed3[cur-i+N-1])
                continue;
            isUsed1[i] = true;
            isUsed2[i+cur] = true;
            isUsed3[cur-i+N-1] = true;
            Queen(cur + 1);
            isUsed1[i] = false;
            isUsed2[cur+i] = false;
            isUsed3[cur-i+N-1] = false;
        }
    }
}
