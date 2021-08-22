import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main11656 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] arr = br.readLine().split("");

        for(int i = arr.length-2 ; i >= 0 ;i--){
                arr[i] += arr[i+1];
            
        }
        

        Arrays.sort(arr, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                return s1.compareTo(s2);
            }
        });

        for(int i = 0 ; i <arr.length ;i++){
            System.out.println(arr[i]);
        
        }
    }
    
}
