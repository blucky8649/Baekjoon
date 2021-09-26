import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main1759 {
    static int L, C;
    static char[] arr;
    static boolean[] isVisited;
    static char[] num;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String str = br.readLine();
        isVisited = new boolean[C];
        arr = new char[C];
        num = new char[L];
        int j = 0;
        for(int i = 0 ; i < arr.length ; i++){
            char cr = str.charAt(j);

            arr[i] = cr;
            j = j + 2;
        }
        Arrays.sort(arr);
        DFS(0, 0, 0);
        System.out.print(sb.toString());


    }
    static void DFS(int depth, int JA, int MO){
        if(depth == L && JA >= 2 && MO >= 1){
            for(int i = 0 ; i < num.length ; i++){
                sb.append(num[i]);
            }
            
            sb.append('\n');
            return;
        }

        if(depth == L){
            return;
        }

        for(int i = 0 ; i < C ; i++){
            if(!isVisited[i] && depth == 0){
                isVisited[i] = true;
                num[depth] = arr[i];
                boolean flag = check(num[depth]);
                DFS(depth + 1, JA + (flag ? 0 : 1), MO + (flag ? 1 : 0));
                isVisited[i] = false;
                num[depth] = 0;
            }else if(!isVisited[i] && num[depth - 1] <= arr[i]){
                isVisited[i] = true;
                num[depth] = arr[i];
                boolean flag = check(num[depth]);
                DFS(depth + 1, JA + (flag ? 0 : 1), MO + (flag ? 1 : 0));
                isVisited[i] = false;
                num[depth] = 0;
            }
        }
        
    }
    static boolean check(char c){
        switch(c){
            case 'a':
            case 'i':
            case 'e':
            case 'o':
            case 'u': return true;
        }
        return false;
    }
    
}
