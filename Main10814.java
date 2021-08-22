import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main10814 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[][] member = new String[n][2];
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            member[i][0] = st.nextToken();
            member[i][1] = st.nextToken();
        }

        Arrays.sort(member, new Comparator<String[]>(){
            public int compare (String[] s1, String[] s2){
                if(Integer.parseInt(s1[0]) > Integer.parseInt(s2[0])){
                    return 1;
                }else if(Integer.parseInt(s1[0]) < Integer.parseInt(s2[0])){
                    return -1;
                }else{
                    return 0;
                }

            }
        });

        for(int i = 0 ; i < n ; i++){
            System.out.println(member[i][0] +" "+ member[i][1]);
        }
    }
    
}
