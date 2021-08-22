import java.io.*;



public class Main1003 {
    public static long[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System. in));

        int n = Integer.parseInt(br.readLine());
        arr = new long[41];
        for(int i = 0; i<arr.length ; i++){
            arr[i] = -1;
        }
        for(int i = 0 ; i < n ; i ++){
            int num = Integer.parseInt(br.readLine());
            fibonacci(num);
            if(num == 0 ){
                System.out.println(0 + " " + 1); 
            }else{
                System.out.println(arr[num-1] + " "+ arr[num]);
            }
            
        }
       
    }
    public static long fibonacci(int n) {
        arr[0] = 0;
        arr[1] = 1;
        
        if(arr[n] == -1){
            arr[n] = fibonacci(n-1) + fibonacci(n-2);
        }
        return arr[n];
       }
}
