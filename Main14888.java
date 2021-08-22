import java.io.*;
import java.util.StringTokenizer;

public class Main14888 {
    public static int N, min=1000000000, max=-1000000000;
    public static int[] arr;
    public static int[] op = new int [4];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < N ; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < 4 ; i++){
            op[i] = Integer.parseInt(st.nextToken());
        }
        Calc(arr[0], 1);
        System.out.println(max);
        System.out.println(min);
    }

    public static void Calc(int num, int idx){
        if (idx == N){
            if(num > max){
                max = num;
            }
             if (num<min){
                min = num;
            }

            return;
        }

        for(int i = 0 ; i < 4 ; i++){
            if(op[i]>0){
                op[i] --;
                switch(i){
                    case 0 : Calc(num + arr[idx], idx + 1); break;
                    case 1 : Calc(num - arr[idx], idx + 1); break;
                    case 2 : Calc(num * arr[idx], idx + 1); break;
                    case 3 : Calc(num / arr[idx], idx + 1); break;
                }
                op[i] ++;
            }
        }
    }
}
