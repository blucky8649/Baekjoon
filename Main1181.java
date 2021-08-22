import java.io.*;
import java.util.Arrays;
import java.util.Comparator;


public class Main1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = new String[n];

        for(int i = 0 ; i < n ; i++){
            str[i] = br.readLine();
        }

        Arrays.sort(str, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                if(s1.length() == s2.length()){
                    return s1.compareTo(s2);
                }else if(s1.length() >  s2.length()){
                    return 1;
                }else{
                    return -1;
                }
                
            }
        });
    StringBuilder sb = new StringBuilder();

    for(int i = 0 ; i < n ; i++){
        
        if (i < n-1 && str[i].equals(str[i+1])){
            continue;
        }else{
            sb.append(str[i]).append('\n');
        }
        
    }
    System.out.println(sb.toString());
}
}
