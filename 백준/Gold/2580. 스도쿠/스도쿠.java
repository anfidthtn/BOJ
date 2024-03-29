import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] checkArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[9][9];
        checkArr = new boolean[27][10];
        for(int i = 0; i < 9; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0){
                    checkArr[i][map[i][j]] = true;
                    checkArr[9 + j][map[i][j]] = true;
                    checkArr[18 + getSection(i, j)][map[i][j]] = true;
                }
            }
        }
        dfs(0, -1);
        for(int i = 0; i < 9; i++) {
        	for(int j = 0; j < 9; j++) {
        		System.out.print(map[i][j] + " ");
        	}
        	System.out.println();
        }
    }

    public static boolean dfs(int row, int col){
    	if(++col == 9) {
    		col = 0;
    		row++;
    	}
        if (row == 9){
            return true;
        }
        if (map[row][col] > 0){
            return dfs(row, col);
        }
        else{
            for(int i = 1; i <= 9; i++){
                if (checkArr[row][i]){
                    continue;
                }
                if (checkArr[9 + col][i]){
                    continue;
                }
                if(checkArr[18 + getSection(row,col)][i]){
                    continue;
                }
                map[row][col] = i;
                checkArr[row][i] = true;
                checkArr[9 + col][i] = true;
                checkArr[18 + getSection(row,col)][i] = true;
                if(dfs(row, col)){
                    return true;
                }
                checkArr[row][i] = false;
                checkArr[9 + col][i] = false;
                checkArr[18 + getSection(row,col)][i] = false;
                map[row][col] = 0;
            }
        }
        return false;
    }
    public static int getSection(int row, int col){
        return row / 3 * 3 + col / 3;
    }
}