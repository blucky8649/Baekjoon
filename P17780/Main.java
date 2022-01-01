package P17780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N, K;
    static ArrayList<Node>[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    }
}
class Node {
    int num;
    int dir;
    Node(int num, int dir) {
        this.num = num;
        this.dir = dir;
    }
}
