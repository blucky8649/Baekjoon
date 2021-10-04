import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main20436 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Key> mapL = new HashMap<>();
        HashMap<String, Key> mapR = new HashMap<>();
        // keyboard setting
        mapL.put("q", new Key(0, 0));
        mapL.put("w", new Key(0, 1));
        mapL.put("e", new Key(0, 2));
        mapL.put("r", new Key(0, 3));
        mapL.put("t", new Key(0, 4));
        mapR.put("y", new Key(0, 5));
        mapR.put("u", new Key(0, 6));
        mapR.put("i", new Key(0, 7));
        mapR.put("o", new Key(0, 8));
        mapR.put("p", new Key(0, 9));

        mapL.put("a", new Key(1, 0));
        mapL.put("s", new Key(1, 1));
        mapL.put("d", new Key(1, 2));
        mapL.put("f", new Key(1, 3));
        mapL.put("g", new Key(1, 4));
        mapR.put("h", new Key(1, 5));
        mapR.put("j", new Key(1, 6));
        mapR.put("k", new Key(1, 7));
        mapR.put("l", new Key(1, 8));

        mapL.put("z", new Key(2, 0));
        mapL.put("x", new Key(2, 1));
        mapL.put("c", new Key(2, 2));
        mapL.put("v", new Key(2, 3));
        mapR.put("b", new Key(2, 4));
        mapR.put("n", new Key(2, 5));
        mapR.put("m", new Key(2, 6));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        String s1 = st.nextToken();
        String s2 = st.nextToken();
        Key left = mapL.get(s1); // 왼손 위치
        Key right = mapR.get(s2); // 오른손 위치

        String target = br.readLine();

        int answer = 0;
        for(int i = 0 ; i < target.length() ; i++){
            String a = String.valueOf(target.charAt(i));
            if(mapL.containsKey(a)){
                int leftDst = Math.abs(left.x - mapL.get(a).x) + Math.abs(left.y - mapL.get(a).y);
                answer += leftDst;
                left = mapL.get(a);
            }else{
                int rightDst = Math.abs(right.x - mapR.get(a).x) + Math.abs(right.y - mapR.get(a).y);
                answer += rightDst;
                right = mapR.get(a);
            }
        }

        System.out.println(answer + target.length());
    }
    
}
class Key{
    int x;
    int y;
    Key (int x, int y){
        this.x =x;
        this.y =y;
    }
}