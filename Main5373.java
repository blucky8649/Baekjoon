import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main5373 {
    static String[][][] cube;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        cube = new String[6][3][3]; //큐브
        String color[] = {"w", "y", "r", "o", "g", "b"};
        

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < t ; i++) {
            // 큐브 초기화
            for (int a = 0 ; a < 6 ; a++) {
                for (int b = 0 ; b < 3 ; b++) {
                    Arrays.fill(cube[a][b], color[a]);
                }
            }
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0 ; j < n ; j++) {
                String dir = st.nextToken(); // 돌리는 방향

                rotate(dir);
            }
            for (int a = 0 ; a < 3 ; a++) {
                for (int b = 0 ; b < 3 ; b++) {
                    sb.append(cube[0][a][b] + " ");
                }
                sb.append("\n");
            } 
        }
        System.out.print(sb.toString());
    }
    public static String[][] selfRotate(String[][] arr) {
		//System.out.println("---COPY---");
		String[][] copy = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				copy[i][j] = arr[2-j][i];
				//System.out.print(copy[i][j]);
			}
			//System.out.println();
		}
		return copy;
	}
    static void rotate(String dir) {
        String a = String.valueOf(dir.charAt(0));
        String b = String.valueOf(dir.charAt(1));

        switch (a) {
            //  U: 윗 면, D: 아랫 면, F: 앞 면, B: 뒷 면, L: 왼쪽 면, R: 오른쪽 면
            case "U" :
                if (b.equals("+")) {
                    String temp1 = cube[2][0][0];
                    String temp2 = cube[2][0][1];
                    String temp3 = cube[2][0][2];

                    cube[0] = selfRotate(cube[0]);

                    cube[2][0][0] = cube[5][0][0];
                    cube[2][0][1] = cube[5][0][1];
                    cube[2][0][2] = cube[5][0][2];

                    cube[5][0][0] = cube[3][0][0];
                    cube[5][0][1] = cube[3][0][1];
                    cube[5][0][2] = cube[3][0][2];

                    cube[3][0][0] = cube[4][0][0];
                    cube[3][0][1] = cube[4][0][1];
                    cube[3][0][2] = cube[4][0][2];
 
                    cube[4][0][0] = temp1;
                    cube[4][0][1] = temp2;
                    cube[4][0][2] = temp3;
                    
                } else {
                    String temp1 = cube[2][0][0];
                    String temp2 = cube[2][0][1];
                    String temp3 = cube[2][0][2];

                    cube[0] = selfRotate(cube[0]);
                    cube[0] = selfRotate(cube[0]);
                    cube[0] = selfRotate(cube[0]);
                    
                    cube[2][0][0] = cube[4][0][0];
                    cube[2][0][1] = cube[4][0][1];
                    cube[2][0][2] = cube[4][0][2];

                    cube[4][0][0] = cube[3][0][0];
                    cube[4][0][1] = cube[3][0][1];
                    cube[4][0][2] = cube[3][0][2];

                    cube[3][0][0] = cube[5][0][0];
                    cube[3][0][1] = cube[5][0][1];
                    cube[3][0][2] = cube[5][0][2];

                    cube[5][0][0] = temp1;
                    cube[5][0][1] = temp2;
                    cube[5][0][2] = temp3;
                }
                break;
            case "D" :
                if (b.equals("-")) {
                    String temp1 = cube[2][2][0];
                    String temp2 = cube[2][2][1];
                    String temp3 = cube[2][2][2];

                    cube[1] = selfRotate(cube[1]);
                    cube[1] = selfRotate(cube[1]);
                    cube[1] = selfRotate(cube[1]);

                    cube[2][2][0] = cube[5][2][0];
                    cube[2][2][1] = cube[5][2][1];
                    cube[2][2][2] = cube[5][2][2];
   
                    cube[5][2][0] = cube[3][2][0];
                    cube[5][2][1] = cube[3][2][1];
                    cube[5][2][2] = cube[3][2][2];
   
                    cube[3][2][0] = cube[4][2][0];
                    cube[3][2][1] = cube[4][2][1];
                    cube[3][2][2] = cube[4][2][2];

                    cube[4][2][0] = temp1;
                    cube[4][2][1] = temp2;
                    cube[4][2][2] = temp3;
                    
                } else {
                    String temp1 = cube[2][2][0];
                    String temp2 = cube[2][2][1];
                    String temp3 = cube[2][2][2];

                    cube[1] = selfRotate(cube[1]);

                    cube[2][2][0] = cube[4][2][0];
                    cube[2][2][1] = cube[4][2][1];
                    cube[2][2][2] = cube[4][2][2];
   
                    cube[4][2][0] = cube[3][2][0];
                    cube[4][2][1] = cube[3][2][1];
                    cube[4][2][2] = cube[3][2][2];

                    cube[3][2][0] = cube[5][2][0];
                    cube[3][2][1] = cube[5][2][1];
                    cube[3][2][2] = cube[5][2][2];

                    cube[5][2][0] = temp1;
                    cube[5][2][1] = temp2;
                    cube[5][2][2] = temp3;
                }
                break;
            case "F" :
                // 위0 아래1 왼4 오른쪽5 
                if (b.equals("+")) {
                    String temp1 = cube[0][2][0];
                    String temp2 = cube[0][2][1];
                    String temp3 = cube[0][2][2];

                    cube[2] = selfRotate(cube[2]);

                    cube[0][2][0] = cube[4][2][2];
                    cube[0][2][1] = cube[4][1][2];
                    cube[0][2][2] = cube[4][0][2];

                    cube[4][0][2] = cube[1][0][0];
                    cube[4][1][2] = cube[1][0][1];
                    cube[4][2][2] = cube[1][0][2];

                    cube[1][0][0] = cube[5][2][0];
                    cube[1][0][1] = cube[5][1][0];
                    cube[1][0][2] = cube[5][0][0];

                    cube[5][0][0] = temp1;
                    cube[5][1][0] = temp2;
                    cube[5][2][0] = temp3;
                    
                } else {
                    String temp1 = cube[0][2][0];
                    String temp2 = cube[0][2][1];
                    String temp3 = cube[0][2][2];

                    cube[2] = selfRotate(cube[2]);
                    cube[2] = selfRotate(cube[2]);
                    cube[2] = selfRotate(cube[2]);

                    cube[0][2][0] = cube[5][0][0];
                    cube[0][2][1] = cube[5][1][0];
                    cube[0][2][2] = cube[5][2][0];

                    cube[5][0][0] = cube[1][0][2];
                    cube[5][1][0] = cube[1][0][1];
                    cube[5][2][0] = cube[1][0][0];

                    cube[1][0][0] = cube[4][0][2];
                    cube[1][0][1] = cube[4][1][2];
                    cube[1][0][2] = cube[4][2][2];

                    cube[4][0][2] = temp3;
                    cube[4][1][2] = temp2;
                    cube[4][2][2] = temp1;
                }
                break;
            case "B" : 
                    // 위0 아래1 왼4 오른쪽5 
                    if (b.equals("+")) {
                        String temp1 = cube[0][0][0];
                        String temp2 = cube[0][0][1];
                        String temp3 = cube[0][0][2];

                        cube[3] = selfRotate(cube[3]);

                        cube[0][0][0] = cube[5][0][2];
                        cube[0][0][1] = cube[5][1][2];
                        cube[0][0][2] = cube[5][2][2];

                        cube[5][0][2] = cube[1][2][2];
                        cube[5][1][2] = cube[1][2][1];
                        cube[5][2][2] = cube[1][2][0];

                        cube[1][2][0] = cube[4][0][0];
                        cube[1][2][1] = cube[4][1][0];
                        cube[1][2][2] = cube[4][2][0];

                        cube[4][0][0] = temp3;
                        cube[4][1][0] = temp2;
                        cube[4][2][0] = temp1;
                        
                    } else {
                        String temp1 = cube[0][0][0];
                        String temp2 = cube[0][0][1];
                        String temp3 = cube[0][0][2];

                        cube[3] = selfRotate(cube[3]);
                        cube[3] = selfRotate(cube[3]);
                        cube[3] = selfRotate(cube[3]);

                        cube[0][0][0] = cube[4][2][0];
                        cube[0][0][1] = cube[4][1][0];
                        cube[0][0][2] = cube[4][0][0];

                        cube[4][0][0] = cube[1][2][0];
                        cube[4][1][0] = cube[1][2][1];
                        cube[4][2][0] = cube[1][2][2];

                        cube[1][2][0] = cube[5][2][2];
                        cube[1][2][1] = cube[5][1][2];
                        cube[1][2][2] = cube[5][0][2];

                        cube[5][0][2] = temp1;
                        cube[5][1][2] = temp2;
                        cube[5][2][2] = temp3;
                    }
                break;
            case "L" : 
                // 위0 아래1 앞2 뒤3 
                if (b.equals("+")) {
                    String temp1 = cube[0][0][0];
                    String temp2 = cube[0][1][0];
                    String temp3 = cube[0][2][0];

                    cube[4] = selfRotate(cube[4]);

                    cube[0][0][0] = cube[3][2][2];
                    cube[0][1][0] = cube[3][1][2];
                    cube[0][2][0] = cube[3][0][2];

                    cube[3][2][2] = cube[1][0][0];
                    cube[3][1][2] = cube[1][1][0];
                    cube[3][0][2] = cube[1][2][0];

                    cube[1][0][0] = cube[2][0][0];
                    cube[1][1][0] = cube[2][1][0];
                    cube[1][2][0] = cube[2][2][0];

                    cube[2][0][0] = temp1;
                    cube[2][1][0] = temp2;
                    cube[2][2][0] = temp3;
                    
                } else {
                    String temp1 = cube[0][0][0];
                    String temp2 = cube[0][1][0];
                    String temp3 = cube[0][2][0];

                    cube[4] = selfRotate(cube[4]);
                    cube[4] = selfRotate(cube[4]);
                    cube[4] = selfRotate(cube[4]);

                    cube[0][0][0] = cube[2][0][0];
                    cube[0][1][0] = cube[2][1][0];
                    cube[0][2][0] = cube[2][2][0];

                    cube[2][0][0] = cube[1][0][0];
                    cube[2][1][0] = cube[1][1][0];
                    cube[2][2][0] = cube[1][2][0];

                    cube[1][0][0] = cube[3][2][2];
                    cube[1][1][0] = cube[3][1][2];
                    cube[1][2][0] = cube[3][0][2];

                    cube[3][2][2] = temp1;
                    cube[3][1][2] = temp2;
                    cube[3][0][2] = temp3;
                }
                break;
            case "R" : 
                    // 위0 아래1 앞2 뒤3 
                    if (b.equals("+")) {
                        String temp1 = cube[0][0][2];
                        String temp2 = cube[0][1][2];
                        String temp3 = cube[0][2][2];

                        cube[5] = selfRotate(cube[5]);

                        cube[0][0][2] = cube[2][0][2];
                        cube[0][1][2] = cube[2][1][2];
                        cube[0][2][2] = cube[2][2][2];

                        cube[2][0][2] = cube[1][0][2];
                        cube[2][1][2] = cube[1][1][2];
                        cube[2][2][2] = cube[1][2][2];

                        cube[1][0][2] = cube[3][2][0];
                        cube[1][1][2] = cube[3][1][0];
                        cube[1][2][2] = cube[3][0][0];

                        cube[3][2][0] = temp1;
                        cube[3][1][0] = temp2;
                        cube[3][0][0] = temp3;
                        
                    } else {
                        String temp1 = cube[0][0][2];
                        String temp2 = cube[0][1][2];
                        String temp3 = cube[0][2][2];

                        cube[5] = selfRotate(cube[5]);
                        cube[5] = selfRotate(cube[5]);
                        cube[5] = selfRotate(cube[5]);

                        cube[0][0][2] = cube[3][2][0];
                        cube[0][1][2] = cube[3][1][0];
                        cube[0][2][2] = cube[3][0][0];

                        cube[3][2][0] = cube[1][0][2];
                        cube[3][1][0] = cube[1][1][2];
                        cube[3][0][0] = cube[1][2][2];

                        cube[1][0][2] = cube[2][0][2];
                        cube[1][1][2] = cube[2][1][2];
                        cube[1][2][2] = cube[2][2][2];

                        cube[2][0][2] = temp1;
                        cube[2][1][2] = temp2;
                        cube[2][2][2] = temp3;
                    }
                break;
        }


    }
}
