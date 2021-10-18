import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main17140 {
    static final int maxSize = 100;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()); 
        // arr[r][c] == k 가 되기 위한 최소시간을 구해야 함
        
        int[][] map = new int[maxSize][maxSize]; // 3 * 3 배열
        for (int i = 0 ; i < 3 ; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < 3 ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if (map[r - 1][c - 1] == k) {
            System.out.println(0);
            return;
        }


        // i : 시간 (초 단위)
        int maxWidth = 0;
        int maxHeight = 0;
        for (int i = 1 ; i <= maxSize ; i++) {
            int maxA = 0;
            int maxB = 0;
            // 행의 개수 >= 열의 개수 : R 연산
            if (maxHeight >= maxWidth) {
                for (int a = 0 ; a< maxSize ; a++) {
                    PriorityQueue<OP> q = new PriorityQueue<>();
                    int[] num = new int[101];

                    for (int b = 0 ; b < maxSize ; b++) {
                        num[map[a][b]]++;
                    }
                    // 숫자 갯수를 세어 PQ에 저장
                    for (int b = 1 ; b <= map[0].length ; b++) {
                        if (num[b] == 0) continue;
                        q.offer(new OP(b, num[b]));
                    }
                    // PQ에 담긴 내용을 차례대로 배열에 다시 저장
                    
                    Arrays.fill(map[a], 0);
                    for (int b = 0 ; b < map[0].length ; b++) {
                        if (q.isEmpty()) {
                            break;
                        }
                        OP d = q.poll();
                        map[a][b++] = d.num;
                        map[a][b] = d.cnt;
                        maxA = Math.max(maxA, b);
                        maxB = Math.max(maxB, a);
                    }
                }
                maxB++;
                maxA++;
            }
            // C 연산
            else {
                for (int a = 0 ; a < maxSize ; a++) {
                    PriorityQueue<OP> q = new PriorityQueue<>();
                    int[] num = new int[101];
                    for (int b = 0 ;  b < maxSize ; b++) {
                        num[map[b][a]]++;
                    }
                    // 숫자 갯수를 세어 PQ에 저장
                    for (int b = 1 ; b <= map.length ; b++) {
                        if (num[b] == 0) continue;
                        q.offer(new OP(b, num[b]));
                    }
                    // PQ에 담긴 내용을 차례대로 배열에 다시 저장
                    for (int b = 0 ; b < map.length ; b++) {
                        if (q.isEmpty()) {
                            map[b][a] = 0;
                        } else {
                            OP d = q.poll();
                            map[b++][a] = d.num;
                            map[b][a] = d.cnt;
                            maxB = Math.max(maxB, b);
                            maxA = Math.max(maxA, a);
                        }
                    }
                }
                maxA++;
                maxB++;
            }
            maxWidth = maxA;
            maxHeight = maxB;
            // arr[r][c] == k 라면 시간 출력하고 종료.
            if (map[r - 1][c - 1] == k) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1); // 100초 안에 못 구하면 -1 출력
    }
}
class OP implements Comparable<OP>{
    int num;
    int cnt;
    OP(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
    @Override
    public int compareTo(OP o) {
        if (this.cnt == o.cnt) {
            return this.num - o.num;
        }
        return this.cnt - o.cnt;
    }
}
