import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 2][N + 2];
		
		for(int i = 1; i <= N; i++) {
			String data = br.readLine();
			for(int j = 1; j <= N; j++) {
				map[i][j] = data.charAt(j - 1) - '0';
			}
		}
		
		ArrayList<Integer> countList = new ArrayList<>();
		Queue<Integer[]> searchPointQueue = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++){
				if (map[i][j] == 1) {
					map[i][j] = 0;
					int count = 1;
					searchPointQueue.offer(new Integer[] {i, j});
					while (!searchPointQueue.isEmpty()) {
						Integer[] point = searchPointQueue.poll();
						for (int d = 0; d < 4; d++) {
							if (map[point[0] + dx[d]][point[1] + dy[d]] == 1) {
								map[point[0] + dx[d]][point[1] + dy[d]] = 0;
								count++;
								searchPointQueue.offer(new Integer[] {point[0] + dx[d], point[1] + dy[d]});
							}
						}
					}
					countList.add(count);
				}
			}
		}
		countList.sort(Integer::compareTo);
		System.out.println(countList.size());
		for (int num : countList) {
			System.out.println(num);
		}
	}
}