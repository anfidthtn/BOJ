class Solution {
	public static void main(String[] args){
		System.out.print(new Solution().solution(new int[][] {
			{5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5}
		}, new int[][] {
			{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}
		}));
	}
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length;
        int M = board[0].length;
        int[][] values = new int[N + 1][M + 1];
        for(int[] sk : skill) {
        	int value = (sk[0] * 2 - 3) * sk[5];
        	values[sk[1]][sk[2]] += value;
        	values[sk[1]][sk[4] + 1] -= value;
        	values[sk[3] + 1][sk[2]] -= value;
        	values[sk[3] + 1][sk[4] + 1] += value;
        }
        for(int i = 0; i < N; i++) {
        	for(int j = 1; j < M; j++) {
        		values[i][j] += values[i][j - 1];
        	}
        }
        for(int j = 0; j < M; j++) {
        	for(int i = 1; i < N; i++) {
        		values[i][j] += values[i - 1][j];
        	}
        }
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < M; j++) {
        		if (board[i][j] + values[i][j] > 0) {
        			answer++;
        		}
        	}
        }
        return answer;
    }
}