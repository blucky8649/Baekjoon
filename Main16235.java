import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main16235 {
    static int N, M, K;
    static int[][] map, nutmap;
    static ArrayList<Tree> trees = new ArrayList<>();
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1}; //↖, ↑, ↗, ←, →, ↙, ↓, ↘ (상하좌우, 대각선) 방향
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        nutmap = new int[N + 1][N + 1];
  
        for(int i = 1 ; i <= N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1 ; j <= N ; j++){
                map[i][j] = 5;
                nutmap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
            


        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, z));
        }

        
        System.out.println(go(K));
    }
    static int go(int K){
        /*
        * ---------<나무 제테크 계절별 특징>
        * 1. 봄 : 자신의 나이만큼 양분을 먹고 나이가 1증가(단 같은 칸에 여러 개의 나무가 있을 시 나이 적은 순으로)
        *  - 만약 먹을 양분이 없다면 그 나무는 죽음.
        * 2. 여름 : 봄에 죽은 나무가 양분으로 변함.. 죽은 나무마다 나이를 2로 나눈 값이 죽은 위치의 양분이 됨.
        * 3. 가을 : 나무가 인접한 8개의 땅(상하좌우, 대각선)에 나이가 1인 나무가 생긴다. (단 나무의 나이가 5의 배수여야 함)
        * 4. 겨울 : 각 칸에 입력받은 값에 맞는 양분이 추가됨.
        */

        for(int year = 0 ; year < K ; year++){
            Collections.sort(trees);
        //1. 봄
        ArrayList<Tree> deadTrees = new ArrayList<>();
        ArrayList<Tree> liveTrees = new ArrayList<>();
        for(int i = 0 ; i < trees.size(); i++){
            Tree tmp = trees.get(i);
            int x = tmp.x;
            int y = tmp.y;
            int age = tmp.age;

            if(map[y][x] >= age){
                map[y][x] -= age;
                age ++;
                liveTrees.add(new Tree(y, x, age));
            }
            else{
                deadTrees.add(new Tree(y, x, age));
            }

        }

        //2. 여름
        if(deadTrees.size() > 0){
            for(Tree deadTree : deadTrees){
                int x = deadTree.x;
                int y = deadTree.y;
                int age = deadTree.age;

                map[y][x] += (age/2);
            }

        }
        
        //3. 가을
        for(int i = 0 ; i < liveTrees.size() ; i++){
            Tree tmp = liveTrees.get(i);

            if(tmp.age % 5 == 0){ // 나이가 5의 배수이면
                for(int j =0 ;j < dx.length ; j++){
                    int nx = tmp.x + dx[j];
                    int ny = tmp.y + dy[j];
                    if(nx >= 1  && nx <= N && ny >= 1 && ny<= N){
                        liveTrees.add(new Tree(nx, ny, 1)); //나무 주변 8방에 1렙 귀요미 생성
                    }
                }
            }
        }
        //4. 겨울
        for(int i = 1 ; i <= N ; i++){
            for(int j = 1 ; j <= N ;j++){
                map[i][j] += nutmap[i][j];
            }

        }
        trees = liveTrees;
    }
    return trees.size();
    }
}
class Tree implements Comparable<Tree>{
    int x, y, age;
    Tree(int x, int y,int age){
        this.x = x;
        this.y = y;
        this.age =age;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "x=" + x +
                ", y=" + y +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Tree o) {
        return this.age-o.age;
    }
}