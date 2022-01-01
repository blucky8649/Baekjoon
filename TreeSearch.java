import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Noode {
    char data;
    Noode left;
    Noode right;
}

class Tree1 {
    public Noode root;
    public void setRoot(Noode node) {
        this.root = node;
    }
    public Noode getRoot() {
        return root;
    }
    public Noode makeNode(char data, Noode left, Noode right) {
        Noode node = new Noode();
        node.data = data;
        node.left = left;
        node.right = right;
        return node;
    }
    public void inorder(Noode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.data);
            inorder(node.right);
        }
    }
    public void preorder(Noode node) {
        if (node != null) {
            System.out.println(node.data);
            preorder(node.left);
            preorder(node.right);
        }
    }
    public void postorder(Noode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.data);
        }
    }
}


public class TreeSearch {
    static TreeMap<String, String[]> map = new TreeMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 1 ; i <= n ; i++) {
            String[] str = br.readLine().split(" ");
            String node[] = new String[2];
            node[0] = str[1];
            node[1] = str[2];
            map.put(str[0], node);
        }
        preorder("A");
        System.out.println();
        inorder("A");
        System.out.println();
        postorder("A");
    }

    static void inorder(String root) {
        if (root.equals(".")) return;
            inorder(map.get(root)[0]);
            System.out.print(root);
            inorder(map.get(root)[1]);
    }
    static void preorder(String root) {
        if (root.equals(".")) return;
            System.out.print(root);
            preorder(map.get(root)[0]);
            preorder(map.get(root)[1]);
    }
    static void postorder(String root) {
        if (root.equals(".")) return;
            postorder(map.get(root)[0]);
            postorder(map.get(root)[1]);
            System.out.print(root);
    }
}
