import java.io.*;

public class Main2748 {
    public static long[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new long[n+1];
        for(int i = 0 ; i < arr.length; i++){
            arr[i] = -1;
        }
       arr[0] = 0;
       arr[1] = 1;
        
        System.out.println(FIB(n));
        
    }
    public static long FIB(int n){
        if(arr[n] == -1){
            arr[n] = FIB(n- 1) + FIB(n - 2);
        }
        return arr[n];
    }
}
