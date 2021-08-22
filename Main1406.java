import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main1406 {
    public static LinkedList<String> list = new LinkedList<>();
    public static ListIterator<String> liter;
    public static int cursor;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        

        //리스트 삽입
        for(int i = 0 ; i< str.length() ; i++){
            list.add(String.valueOf(str.charAt(i)));
        }
        int n = Integer.parseInt(br.readLine());
        cursor = str.length();
        liter = list.listIterator();
        while(liter.hasNext()) {
			liter.next();
		}


        //반복입력
        for(int i = 0 ; i < n ; i++){
            
            st = new StringTokenizer(br.readLine(), " ");

            switch(st.nextToken()){
               
                case "L" : L(); break;
                case "D" : D(); break;
                case "B" : B(); break;
                case "P" : liter.add(st.nextToken());  break;
                default : break;
            } 
            
        }

        for(String str2 : list){
            sb.append(str2);
        }

        System.out.println(sb.toString());
    }
    public static void L(){
        if(liter.hasPrevious()){
            liter.previous();
        }
    }

    public static void D(){
        if(liter.hasNext()){
            liter.next();
        }
    }
    public static void B(){
        if(liter.hasPrevious()){
            liter.previous();
            liter.remove();
        }
    }
    

}
