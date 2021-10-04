import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1062 {
    static int n, k, answer = Integer.MIN_VALUE;
    static boolean[] isVisited;
    static String[] words;
    public static void main(String[] args) throws IOException{ 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new String[n];
        for(int i = 0 ; i < n ; i++){
            String word = br.readLine();
            
            word = word.replace("anta", "");
            word = word.replace("tica", "");
            words[i] = word;
        }


        if(k < 5) { //a c i n t의 개수가 5개이므로
            System.out.println("0");
            return;
        } else if(k == 26) { //모든 알파벳을 다 읽을 수 있다.
            System.out.println(n);
            return;
        }


        isVisited = new boolean[26];
        isVisited['a' - 'a'] = true;
        isVisited['n' - 'a'] = true;
        isVisited['t' - 'a'] = true;
        isVisited['c' - 'a'] = true;
        isVisited['i' - 'a'] = true;

        DFS(0, 0);
        System.out.println(answer);
    }
    static void DFS(int idx, int depth){
        if(depth == k - 5){
            int count = 0;
            for(int i = 0 ; i < words.length ; i++){
                boolean readable = true;
                for(int j = 0 ; j < words[i].length() ; j++){
                    if(!isVisited[words[i].charAt(j) - 'a']){
                        readable = false;
                        break;
                    }
                }
                if(readable) count ++;
            }
            answer = Math.max(answer, count);
            return;
        }


        for(int i = idx ; i < 26 ; i++){
            if(!isVisited[i]){
                isVisited[i] = true;
                DFS(i, depth + 1);
                isVisited[i] = false;
            }
        }
    }
}
