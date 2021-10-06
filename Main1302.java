import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

import jdk.nashorn.api.tree.Tree;

public class Main1302 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0 ; i < n ; i++) {
            String str = br.readLine();
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }
        Integer max = Collections.max(map.values());
        
        TreeSet<String> set = new TreeSet<>();
        for (String s : map.keySet()) {
            if (map.get(s) == max) {
                set.add(s);
            }
        }

        for (String s2 : set) {
            System.out.println(s2);
            break;
        }
        
    }   
    
}
