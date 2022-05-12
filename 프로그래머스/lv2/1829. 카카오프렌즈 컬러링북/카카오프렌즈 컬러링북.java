
class Solution {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];
        int[][] copy = new int[m][n];
        for(int i = 0 ; i < m; i++) {
        	for(int j = 0; j < n; j++) {
        		copy[i][j] = picture[i][j];
        	}
        }
        for(int i = 0 ; i < m; i++) {
        	for(int j = 0; j < n; j++) {
        		if (copy[i][j] > 0) {
        			int color = copy[i][j];
        			copy[i][j] = 0;
        			answer[0]++;
        			dfs(answer, copy, i, j, color, new int[] {1});
        		}
        	}
        }
        return answer;
    }
    public static void dfs(int[] answer, int[][] picture, int row, int col, int color, int[] count) {
    	if (answer[1] < count[0]) {
    		answer[1] = count[0];
    	}
    	for(int d = 0; d < 4; d++) {
    		int nextRow = row + dr[d];
    		int nextCol = col + dc[d];
    		if (nextRow < 0 || nextCol < 0 || nextRow >= picture.length || nextCol >= picture[0].length) {
    			continue;
    		}
    		if (picture[nextRow][nextCol] != color) {
    			continue;
    		}
    		picture[nextRow][nextCol] = 0;
    		count[0]++;
    		dfs(answer, picture, nextRow, nextCol, color, count);
    	}
    }
}