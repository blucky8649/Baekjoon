
import java.io.IOException;
import java.util.*;

public class Main1789 {
    static boolean isVisited[];
    static HashMap<String, Integer> map;
    public static void main(String[] args) throws IOException{
        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = {2,3,5};

        ArrayList<String> list = new ArrayList<>();
        for(int i = 0 ; i < course.length ; i++){
            map = new HashMap<>();
            for(int j = 0 ; j < orders.length ; j++){
                isVisited = new boolean[orders[j].length()];
                mc(orders[j], course[i], 0, 0);
            }
            int max = !map.isEmpty()  ? Collections.max(map.values()) : 0;
            
            
            for(String key : map.keySet()){
                //겹치는게 2개 이상이어야 함!
                if(max >= 2 && map.get(key) == max){
                    list.add(key);
                }
            }
        }
 
        String[] answer = new String[list.size()];
        for(int i = 0 ; i < list.size() ; i++){
            answer[i] = list.get(i);
        }
        Arrays.sort(answer);

        System.out.println(Arrays.toString(answer));
    }
    static void mc (String order, int course, int depth, int idx){
        if(depth == course){
            String tmp = "";
            for(int i = 0 ; i < isVisited.length; i++){
                if(isVisited[i]){
                    tmp += String.valueOf(order.charAt(i));
                }
            }
            char[] combChars = tmp.toString().toCharArray();
            Arrays.sort(combChars);
            tmp = "";
            for(char c : combChars){
                tmp += c;
            }
            map.put(tmp, map.containsKey(tmp) ? map.get(tmp) + 1 : 1);
            return;
        }
        
        for(int i = idx ; i < order.length() ; i++){
            if(!isVisited[i]){
                isVisited[i] = true;
                mc(order, course, depth + 1, i + 1);
                isVisited[i] = false;
            }
        }
    }
}
