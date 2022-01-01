import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main23291 {
    static int n, k;
    static Integer[][] map;
    static int[][] arr;
    static int[] dy = {0, 1};
    static int[] dx = {1, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 물고기가 가장 많이 들어있는 어항과 가장 적게 들어있는 어항의 물고기 수 차이가 K 이하가 되려면 어항을 몇 번 정리해야하는지 구해보자.
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new Integer[1][n];
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < n ; i++) {
            map[map.length - 1][i] = Integer.parseInt(st.nextToken());
        }
        int ans = 1;
        while (true) {
            // System.out.println("<<<<<<<<<<<<"+ans+">>>>>>>>>>>>");
            // 1. 물고기 추가
            add_fish();
            // 2. 어항 쌓기
            add_block();
            // System.out.println("---------add_block() CLEAR-------");
            // 3. 물고기 수 조정
            adj_fish();
            // System.out.println("---------adj_fish() CLEAR-------");
            // 4. 어항 공중 부양
            fly_fish();
            // System.out.println("---------fly_fish() CLEAR-------");
            adj_fish();
            // System.out.println("---------adj_fish() CLEAR-------");
            int min = Collections.min(Arrays.asList(map[0]));
            int max = Collections.max(Arrays.asList(map[0]));
            if (max - min <= k) break;
            ans++;

        }
        System.out.println(ans);
        
        
    }
    static void fly_fish() {
        Integer[][] clone;
        int width = map[0].length / 2;
        int height = 1;
        for (int t = 0 ; t < 2 ; t++) {
            Integer[][] second_layer = new Integer[height][width];
            for (int i = 0 ; i < height ; i++) {
                for (int j = 0 ; j < width ; j++) {
                    second_layer[height - 1 - i][width - j - 1] = map[i][j]; // 시계방향 회전
                }
            }
            // for (int i = 0 ; i < second_layer.length ; i++) {
            //     System.out.println(Arrays.toString(second_layer[i]));
            // }
            clone = new Integer[height * 2][width];
            for (int i = 0 ; i < height ; i++) {
                for (int j = 0 ; j < width ; j++) {
                    clone[i][j] = second_layer[i][j];
                }
            }
            for (int i = height ; i < clone.length ; i++) {
                for (int j = 0 ; j < clone[0].length ; j++) {
                    clone[i][j] = map[i - height][j + width];
                }
            }
            map = new Integer[clone.length][clone[0].length];
            for (int i = 0 ; i < clone.length ; i++) {
                map[i] = clone[i].clone();
                // System.out.println(Arrays.toString(clone[i]) + "sad");
            }
            height *= 2;
            width /= 2;
            
        }
        

    }
    static void adj_fish() {
        Integer[][] clone = new Integer[map.length][map[0].length];

        for (int i = 0 ; i < clone.length ; i++) {
            clone[i] = map[i].clone();
        }
        for (int i = 0 ; i < map.length ; i++) {
            for (int j = 0 ; j < map[0].length ; j++) {
                if (map[i][j] == null) continue;
                for (int a = 0 ; a < 2 ; a++) {
                    int nx = j + dx[a];
                    int ny = i + dy[a];

                    if (nx >= 0 && ny >= 0 && nx < map[0].length && ny < map.length && map[ny][nx] != null) {
                        int d = map[i][j] - map[ny][nx];
                        if (d > 0) {
                            clone[i][j] -= Math.abs(d) / 5;
                            clone[ny][nx] += Math.abs(d) / 5;
                        }
                        else {
                            clone[i][j] += Math.abs(d) / 5;
                            clone[ny][nx] -= Math.abs(d) / 5;
                        }
                    }
                }
            }
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0 ; i < clone[0].length ; i++) {
            for (int j = clone.length - 1 ; j >= 0 ; j--) {
                if (clone[j][i] == null) continue;
                arr.add(clone[j][i]);
            }
        }
        map = new Integer[1][arr.size()];
        int idx = 0;
        for (Integer a : arr) {
            map[0][idx++] = a;
        }
        // System.out.println(Arrays.toString(map[0]));
    }
    static void add_block() {
        
        int width = 1;
        int height = 1;
        while (true) {
            Integer[][] second_layer = new Integer[height][height];
        
            for (int i = 0 ; i < height ; i++) {
                for (int j = 0 ; j < width ; j++) {
                    second_layer[j][height - i - 1] = map[i][j];
                }
            }
            // System.out.println((map.length) + " " + (map[0].length - width));
            if (map.length > map[0].length - width) break;
            Integer[][] clone = new Integer[width + 1][map[0].length - width];
            for (int i = 0 ; i < height ; i++) {
                for (int j = 0 ; j < height ; j++) {
                    clone[i][j] = second_layer[i][j];
                }
            }
            for (int i = 0 ; i < map[0].length - width ; i++) {
                clone[clone.length - 1][i] = map[map.length - 1][(width)+ i];
            }
            
            int tmp = width;
            width = height;
            height = tmp + 1;

            
            map = new Integer[clone.length][clone[0].length];
            for (int i = 0 ; i < clone.length ; i++) {
                map[i] = clone[i].clone();
                // System.out.println(Arrays.toString(map[i]));
            }
            // System.out.println("---------------------------");
        }
        
    
    }
    static void add_fish() {
        int min = Collections.min(Arrays.asList(map[map.length - 1]));
        for (int i = 0 ; i < n ; i++) {
            if (map[map.length - 1][i] == min) map[map.length - 1][i]++;
        }
    }
}
