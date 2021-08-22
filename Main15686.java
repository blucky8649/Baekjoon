import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main15686 {
    static int N, M, map[][], board[][][], min_cs = Integer.MAX_VALUE;
    static ArrayList<Chicken> clist = new ArrayList<>();
    static ArrayList<Chicken> hlist = new ArrayList<>();
    static Stack<Chicken> selectedCK = new Stack<>();

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++){
                int A = Integer.parseInt(st.nextToken());
                if(A == 2){
                    clist.add(new Chicken(j, i));
                }
                else if(A == 1){
                    hlist.add(new Chicken(j, i));
                }
            }
        }
        DFS(0, 0);
        System.out.println(min_cs);
        
    }
    static void DFS(int start, int K){
        if(K == M){
            calcDst();
            return;
        }

       
         for(int i = start ; i < clist.size() ; i++){
            selectedCK.push(clist.get(i));
            DFS(i + 1,K +1);
            selectedCK.pop();
        }
    }
    
    static void calcDst(){

        int sum = 0;
        
       for(Chicken home : hlist){
           int min = Integer.MAX_VALUE;
           for(Chicken store : selectedCK){

            int dst = Math.abs(home.x - store.x) + Math.abs(home.y - store.y);
            
            min = Math.min(min, dst);
           }
           sum += min;
           if (sum > min_cs) return;
       }
       min_cs = Math.min(min_cs, sum);
    }
}
class Chicken{
    int x;
    int y;
    Chicken(int x, int y){
        this.x = x;
        this.y = y;
    }
}