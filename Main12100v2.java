import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main12100v2 {
    static int n, ans;
    static int[][] map;
    static int[] dirSet = new int[5]; // 5번의 방향정보
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0 ; i < n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < n ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                ans = Math.max(ans, map[i][j]);
            }
        }
        select_dir(0);
        System.out.println(ans);
    }
    static void select_dir(int k) {
        if (k == 5) {
            move();
            return;
        }
        for (int i = 1 ; i <= 4 ; i++) {
            dirSet[k] = i;
            select_dir(k + 1);
        }
    }

    static void move() {
        int[][] clone = new int[n][n];
        Stack<Integer> stack = new Stack<>();
        System.out.println(Arrays.toString(dirSet));
        boolean flag = false; // 이미 한번 합쳤다면 true가 됨
        for (int i = 0 ; i < n ; i++){
            clone[i] = map[i].clone();
        }
        for (int dir : dirSet) {
            // 상하좌우
            switch(dir) {
                case 1 : //상
                   for (int x = 0 ; x < n ; x++) {
                       for (int y = 0 ; y < n ; y++) {
                        // 블록을 발견하면 stack 에 넣는다
                        if (clone[y][x] > 0) {
                            if (stack.isEmpty()) {
                                stack.push(clone[y][x]);
                            } else {
                                if (stack.peek() == clone[y][x] && !flag) {
                                    stack.push(stack.pop() + clone[y][x]);
                                    ans = Math.max(ans, stack.peek());
                                    flag = true;
                                } else {
                                    stack.push(clone[y][x]);
                                }
                            }
                            clone[y][x] = 0;
                            
                        }
                       }
                       flag = false;
                        int index = stack.size() - 1;
                        while(!stack.isEmpty()) {
                            clone[index--][x] = stack.pop();
                       }
                   }
                   System.out.println("000000000000001번-----------");
                    for (int i = 0 ; i < n ; i++) {
                        System.out.println(Arrays.toString(clone[i]));
                    }
                    break;
                case 2 : //하
                    for (int x = 0 ; x < n ; x++) {
                        for (int y = n - 1 ; y >= 0 ; y--) {
                        // 블록을 발견하면 stack 에 넣는다
                        if (clone[y][x] > 0) {
                            if (stack.isEmpty()) {
                                stack.push(clone[y][x]);
                            } else {
                                if (stack.peek() == clone[y][x] && !flag) {
                                    stack.push(stack.pop() + clone[y][x]);
                                    ans = Math.max(ans, stack.peek());
                                    flag = true;
                                } else {
                                    stack.push(clone[y][x]);
                                }
                            }
                            clone[y][x] = 0;

                        }
                        }
                        flag = false;
                        int index = n - stack.size();
                        while(!stack.isEmpty()) {
                            clone[index++][x] = stack.pop();
                        }
                    }
                    System.out.println("000000000000002번-----------");
                    for (int i = 0 ; i < n ; i++) {
                        System.out.println(Arrays.toString(clone[i]));
                    }
                    break;
                case 3 : // 좌
                    for (int y = 0 ; y < n ; y++) {
                        for (int x = 0 ; x < n ; x++) {
                        // 블록을 발견하면 stack 에 넣는다
                        if (clone[y][x] > 0) {
                            if (stack.isEmpty()) {
                                stack.push(clone[y][x]);
                            } else {
                                if (stack.peek() == clone[y][x] && !flag) {
                                    stack.push(stack.pop() + clone[y][x]);
                                    ans = Math.max(ans, stack.peek());
                                    flag = true;
                                } else {
                                    stack.push(clone[y][x]);
                                }
                            }
                            clone[y][x] = 0;

                        }
                        }
                        flag = false;
                        int index = stack.size() - 1;
                        while(!stack.isEmpty()) {
                            clone[y][index--] = stack.pop();
                        }
                    }
                    System.out.println("000000000000003번-----------");
                    for (int i = 0 ; i < n ; i++) {
                        System.out.println(Arrays.toString(clone[i]));
                    }
                    break;
                case 4 : // 우
                    for (int y = 0 ; y < n ; y++) {
                        for (int x = n -1 ; x >= 0 ; x--) {
                        // 블록을 발견하면 stack 에 넣는다
                            if (clone[y][x] > 0) {
                                if (stack.isEmpty()) {
                                    stack.push(clone[y][x]);
                                } else {
                                    if (stack.peek() == clone[y][x] && !flag) {
                                        stack.push(stack.pop() + clone[y][x]);
                                        ans = Math.max(ans, stack.peek());
                                        flag = true;
                                    } else {
                                        stack.push(clone[y][x]);
                                    }
                                }
                                clone[y][x] = 0;
                            }
                        }
                        flag = false;
                        int index = n - stack.size();
                        while(!stack.isEmpty()) {
                            clone[y][index++] = stack.pop();
                        }

                    }
                    System.out.println("000000000000004번-----------");
                    for (int i = 0 ; i < n ; i++) {
                        System.out.println(Arrays.toString(clone[i]));
                    }
                    break;
            }
        }
        
    }
}
