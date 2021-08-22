import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14891 {
    static int[][] gear = new int[4][8];
    static int[][] clone;
    static int[] gearDir;
    static Queue<Gear> q = new LinkedList<>();
    public static void main(String[] args) throws IOException   {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 각각의 톱니바퀴를 2차원배열 형태로 저장한다.(차례대로 12시방향부터 저장된다.)
        for(int i = 0 ; i < 4 ; i++){
            String str = br.readLine();
            for(int j = 0 ; j < 8 ; j++){
                gear[i][j] = str.charAt(j) - '0';
            }
        }

        int n = Integer.parseInt(br.readLine());
        // 2. 기준이 되는 톱니바퀴의 방향정보를 Queue에 저장한다.
        for(int i = 0 ; i < n ; i++){
            StringTokenizer st= new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            q.offer(new Gear(num, dir));
        }
        clone = new int[4][8];
        // 분신을 만든다 : 각 톱니바퀴를 하나하나 씩 돌렸다가 나중에 한꺼번에 결과로 가져오기 위함이다.
        for(int i = 0 ; i < 4 ; i++){
            clone[i] = gear[i].clone();
        }
        while(!q.isEmpty()){
            Gear d = q.poll();
            gearDir = new int[4];

                gearDir[d.num - 1] = d.dir;
                rotate(d.num - 1);                 
                    // 왼쪽 톱니바퀴 비교
                for(int i = d.num - 1 ; i <= 2 ; i++ ){
                    if(gear[i][2] != gear[i+1][6]){
                        gearDir[i+1] = gearDir[i] * -1;
                        rotate(i+1);
                    }
                }
                    // 오른쪽 톱니바퀴 비교
                for(int i = d.num - 1 ; i >= 1 ; i-- ){
                     if(gear[i][6] != gear[i-1][2]){
                       gearDir[i-1] = gearDir[i] * -1;
                       rotate(i-1);
                    }
                 }
                 // 분신들을 이제 실체로 옮기자.
                 for(int i = 0 ; i < 4 ; i++){
                    gear[i] = clone[i].clone();
                } 
         }
          
         System.out.println((gear[0][0] * 1) + (gear[1][0] * 2) + (gear[2][0] * 4) + (gear[3][0] * 8));
        
    }
    static void rotate(int dir){ // 톱니바퀴 돌리는 함수
        switch(gearDir[dir]){
            case 1 : 
                clone[dir][0] = gear[dir][7];
                clone[dir][1] = gear[dir][0];
                clone[dir][2] = gear[dir][1];
                clone[dir][3] = gear[dir][2];
                clone[dir][4] = gear[dir][3];
                clone[dir][5] = gear[dir][4];
                clone[dir][6] = gear[dir][5];
                clone[dir][7] = gear[dir][6];
                break;
            case -1 :
                clone[dir][0] = gear[dir][1];
                clone[dir][1] = gear[dir][2];
                clone[dir][2] = gear[dir][3];
                clone[dir][3] = gear[dir][4];
                clone[dir][4] = gear[dir][5];
                clone[dir][5] = gear[dir][6];
                clone[dir][6] = gear[dir][7];
                clone[dir][7] = gear[dir][0];
                break;

        }
    }
}
class Gear{
    int num;
    int dir;
    Gear(int num, int dir){
        this.num = num;
        this.dir = dir;
    }
}