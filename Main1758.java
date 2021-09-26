import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main1758 {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        Integer n = Integer.parseInt(br.readLine());

        Integer people[] = new Integer[n];
        for(int i = 0 ; i < n ; i++){
            people[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(people, Comparator.reverseOrder());


        int answer = 0;
        for(int i = 0 ; i < n ; i++){
            if(people[i] - i <= 0) {
                break;
            }
            answer += people[i] - i;
        }

        System.out.println(answer);
    }
}