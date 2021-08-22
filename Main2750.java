import java.io.*;

public class Main2750 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Insertion_Sort(arr, n);
        for(int i = 0 ;  i< n ; i ++){
            System.out.println(arr[i]);
        }
        

    }

    public static void Insertion_Sort(int[] arr, int n){
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }	
            arr[j + 1] = key;
        }
    }
    
}
