import java.util.LinkedList;
import java.util.Queue;

class Solution {
	int[] dr = {-1, 0, 1, 0};
	int[] dc = {0, -1, 0, 1};
	class Node{
		int row;
		int col;
		int move;
		public Node(int row, int col, int move) {
			super();
			this.row = row;
			this.col = col;
			this.move = move;
		}
	}
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        tCase : for(int t = 0; t < 5; t++) {
        	char[][] map = new char[7][7];
        	for(int i = 1; i <= 5; i++) {
        		for(int j = 1; j <= 5; j++) {
        			map[i][j] = places[t][i - 1].charAt(j - 1);
        		}
        	}
        	
        	for(int i = 1; i <= 5; i++) {
        		for(int j = 1; j <= 5; j++) {
        			if (map[i][j] == 'P') {
        				Queue<Node> queue = new LinkedList<>();
        				boolean[][] visited = new boolean[7][7];
        				visited[i][j] = true;
        				queue.add(new Node(i, j, 0));
        				while(!queue.isEmpty()) {
        					Node now = queue.poll();
        					for(int d = 0; d < 4; d++) {
        						int nextRow = now.row + dr[d];
        						int nextCol = now.col + dc[d];
        						if (visited[nextRow][nextCol]) {
        							continue;
        						}
        						visited[nextRow][nextCol] = true;
        						if (map[nextRow][nextCol] == 'P') {
        							answer[t] = 0;
        							continue tCase;
        						}
        						if(now.move == 0 && map[nextRow][nextCol] == 'O') {
        							queue.add(new Node(nextRow, nextCol, now.move + 1));
        						}
        					}
        				}
        			}
        		}
        	}
        	answer[t] = 1;
        }
        return answer;
    }
}