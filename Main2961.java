import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2961 {
    static int n, answer = Integer.MAX_VALUE;
    static boolean[] isVisited;
    static ArrayList<Ing> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        isVisited = new boolean[n];
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr.add(new Ing(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for(int i = 1 ; i <= n ; i++){
            DFS(0, 0, i);
        }
        System.out.println(answer);

    }
    static void DFS(int idx, int depth, int target){
        if(depth == target){
            int sin = 1;
            int sun = 0;

            for(int i = 0 ; i < isVisited.length ; i++){
                if(isVisited[i]){
                    sin *= arr.get(i).sin;
                    sun += arr.get(i).sun;
                }
            }
            answer = Math.min(answer, Math.abs(sin - sun));
            return;
        }

        for(int i = 0 ; i < n ; i ++){
            if(!isVisited[i]){
                isVisited[i] = true;
                DFS(i, depth + 1, target);
                isVisited[i] = false;

            }
        }
    }
}
class Ing {
    int sin;
    int sun;
    Ing(int sin, int sun){
        this.sin = sin;
        this.sun = sun;
    }
}