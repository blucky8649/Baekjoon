import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main21918 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int cmd = Integer.parseInt(st.nextToken());
        int[] bulb = new int[n+1]; 
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1 ; i <= n ; i++) {
            bulb[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i< cmd ; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int command = Integer.parseInt(st.nextToken());
            int bulb1 = Integer.parseInt(st.nextToken());
            int bulb2 = Integer.parseInt(st.nextToken());
            switch(command) {
                case 1 :
                    bulb[bulb1] = bulb2;
                    break;
                case 2 :
                    for (int j = bulb1 ; j <= bulb2 ; j++) {
                        if (bulb[j] == 1) {
                            bulb[j] = 0;
                        } else {
                            bulb[j] = 1;
                        }

                    } 
                    break;

                case 3 :
                    for (int j = bulb1 ; j <= bulb2 ; j++) {
                        bulb[j] = 0;

                    }
                    break;
                case 4 :
                    for (int j = bulb1 ; j <= bulb2 ; j++) {
                        bulb[j] = 1;

                    }
                    break;
            }
        }

            StringBuilder sb = new StringBuilder();
            for (int i = 1 ; i <= n ; i++) {
                sb.append(bulb[i]).append(" ");
            }
            System.out.print(sb.toString());
    }
    
}
