import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main14467 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] cow = new int[11];
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());
            if(map.containsKey(num)){
                if(map.get(num) != pos){
                    cow[num]++;
                }
                map.put(num, pos);
            }else{
                map.put(num, pos);
            }

        }
        int ans = 0;
        for(int i = 1 ; i < cow.length ; i++){
            ans += cow[i];
        }

        System.out.println(ans);
    }
    
}
