import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;

public class Main1076 {
    public static void main(String[] args) throws IOException{
        HashMap<String, Reg> map = new HashMap<>();
        map.put("black", new Reg(0, 1));
        map.put("brown", new Reg(1, 10));
        map.put("red", new Reg(2, 100));
        map.put("orange", new Reg(3, 1_000));
        map.put("yellow", new Reg(4, 10_000));
        map.put("green", new Reg(5, 100_000));
        map.put("blue", new Reg(6, 1_000_000));
        map.put("violet", new Reg(7, 10_000_000));
        map.put("grey", new Reg(8, 100_000_000));
        map.put("white", new Reg(9, 1_000_000_000));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        String c = br.readLine();
        String answer = String.valueOf((long)(map.get(a).val * 10 + map.get(b).val) * map.get(c).mul);
        System.out.println(answer);
    }
}

class Reg {
    int val;
    int mul;
    Reg(int val, int mul) {
        this.val = val;
        this.mul = mul;
    }
}