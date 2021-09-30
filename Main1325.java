import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1325 {
    static ArrayList<Integer>[] list;
    static boolean[] check;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        
        list = new ArrayList[n + 1];

        for(int i = 1 ; i <= n ; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < m ; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
        }

        for(int i = 1 ; i <= n ; i++){
            check = new boolean[n + 1];
            BFS(i);
        }

        int max = 0;
        for(int r : arr){
            max = Math.max(max, r);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1 ; i < arr.length ; i++){
            if(arr[i] == max){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
    }
    static void BFS(int i){
        Queue<Integer> q = new LinkedList<>();
        check[i] = true;
        q.offer(i);

        while(!q.isEmpty()){
            int val = q.poll();
            for(int next : list[val]){
                if(!check[next]){
                    check[next] = true;
                    arr[next] ++;
                    q.offer(next);
                }
            }
            
        }

    }
    
}
