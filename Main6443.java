import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main6443 {
    static TreeSet<String> set;
    static String[] words;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            String word = br.readLine();
            words = word.split("");
            isVisited = new boolean[word.length()];
            set = new TreeSet<>();
            DFS(0, 0, word, "");
            for(String str : set){
                sb.append(str + "\n");
            }
        }
        System.out.print(sb.toString());

    }
    static void DFS(int index, int depth, String word, String ans){
        if(depth == word.length()){
            set.add(ans);
            return;
        }

        for(int i = 0 ; i < word.length(); i++){
            if(!isVisited[i]){
                isVisited[i] = true;
                DFS(i, depth + 1, word, ans + words[i]);
                isVisited[i] = false;
            }
        }
    }
}
