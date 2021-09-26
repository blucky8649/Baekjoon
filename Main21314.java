import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main21314 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Min> arrM = new ArrayList<>();
        ArrayList<Min> arrK = new ArrayList<>();

        String str =br.readLine();
        arrM.add(new Min(1, "M"));
        arrK.add(new Min(5, "K"));
        //1. 민겸 수 리스트 만들기
        for(int i = 1 ; i < str.length() ;i++){
            arrM.add(new Min(arrM.get(i-1).key * 10, "M" + arrM.get(i-1).value)); // M, MM, MMM ...          
            arrK.add(new Min(arrK.get(i-1).key * 10, "M" + arrK.get(i-1).value)); // K, MK, MMK ...
        }
       
        // 2. 작은 수 만들기
        //2-1 문자열 M -> K 순 처리
        String min = str;
        int index = arrM.size()-1;
        while(index >= 0){
            if(min.contains(arrM.get(index).value)){
                min = min.replace(arrM.get(index).value, String.valueOf(arrM.get(index).key));
            }else{
                index --;
            }
        }
        index = 0;
        min = min.replace(arrK.get(0).value, String.valueOf(arrK.get(0).key));
        

        // 3. 큰 수 만들기!
        //2-1 문자열 K -> M 순 처리
        String max = str;
        index = arrM.size()-1;
        while(index >= 0){
            if(max.contains(arrK.get(index).value)){
                max = max.replace(arrK.get(index).value, String.valueOf(arrK.get(index).key));
            }else{
                index --;
            }
        }
        max = max.replace(arrM.get(0).value, String.valueOf(arrM.get(0).key));
        
        
        System.out.println(max);
        System.out.println(min);

    }   
    
}
class Min{
    int key;
    String value;
    Min(int key, String value){
        this.key = key;
        this.value = value;
    }
}