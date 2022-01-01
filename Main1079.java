import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.swing.SpringLayout;

public class Main1079 {
    static int N;
    static ArrayList<Integer> participants = new ArrayList<>();
    static int[][] point;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < N ; i++) {
            participants.add(Integer.parseInt(st.nextToken()));
        }

        point = new int[N][N];
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < N ; j++) {
                point[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int Mafia = Integer.parseInt(br.readLine());
        ArrayList<Integer> citizen = new ArrayList<>(participants);
        citizen.set(Mafia, -1);
        
        int night = 0;
        while (true) {
            int dead = -1;
            if (participants.isEmpty()) {
                System.out.println(night);
                break;
            }
            Collections.sort(participants);
            int d = 0;
            int max = Collections.max(citizen);
            switch (participants.size() % 2) {
                case 0 : 
                    night ++; // 밤
                    for (int i = 0 ; i < participants.size(); i++) {
                        if (participants.get(i) == -1 || i == Mafia) continue;
                        if (participants.get(i) == max) {
                            participants.set(i, -1);
                            citizen.set(i, -1);
                            dead = i;
                            break;
                        }
                    }
                    break;
                case 1 : 
                    // 낮
                    for (int i = 0 ; i < participants.size() ; i++) {
                        
                    }

            }
            // 유죄지수 변경
            for (int i = 0 ; i < participants.size() ; i++) {
                if (i != Mafia && citizen.get(i) == -1) continue;
                participants.set(i, point[dead][i]);
                if (i != Mafia) {
                    citizen.set(i, point[dead][i]);
                }
                
            }
        }
    }
}

class People implements Comparable<People>{
    int num;
    int point;
    People (int num, int point) {
        this.num = num;
        this.point = point;
    }
    @Override
    public int compareTo(People o) {
        return o.point - this.point;
    }
    
}
