import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main11651 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[][] group = new int[n][2];
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            group[i][0] = Integer.parseInt(st.nextToken());
            group[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(group,new Comparator<int[]>(){
            @Override
            public int compare(int[] n1, int[] n2){
                if(n1[1] == n2[1]){
                    return Integer.compare(n1[0], n2[0]);
                }else{
                    return Integer.compare(n1[1], n2[1]);
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++){
            sb.append(group[i][0]+ " " + group[i][1]).append("\n");
        }

        System.out.println(sb.toString());
    }
    
}
