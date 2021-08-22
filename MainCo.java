public class MainCo {
    public static void main(String[] args) {
        int ans = 0, cnt = 0;
        boolean flag = false;
        String b1 = Integer.toBinaryString(2147483600);

        for(int i = 0 ; i < b1.length(); i++){
            if(b1.charAt(i) == '1' && !flag){
                flag = true;
            }

            if(flag && b1.charAt(i) == '0'){
                cnt++;
                

            }

            if(flag && b1.charAt(i) == '1'){
                ans = Math.max(ans, cnt);
                cnt = 0;
            }
        }
        System.out.println(b1);
        
    }
    
}
