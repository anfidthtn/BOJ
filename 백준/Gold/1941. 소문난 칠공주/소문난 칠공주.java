import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] map = new String[5];
		for(int i = 0; i < 5; i++) {
			map[i] = br.readLine();
		}
		System.out.println(makeSet(map, 0, 0, 0));
	}
	public static int makeSet(String[] map, int select, int now, int count) {
		if (count == 7) {
			if (check(map, select)) {
				return 1;
			}
			return 0;
		}
		if (now - count >= 19) {
			return 0;
		}
		return makeSet(map, select | (1 << now), now + 1, count + 1) +
		makeSet(map, select, now + 1, count);
	}
	public static boolean check(String[] map, int select) {
		int now = -1;
		int countY = 0;
		for(int bit = 0; bit < 25; bit++) {
			if ((select & (1 << bit)) != 0) {
				if (map[bit / 5].charAt(bit % 5) == 'Y') {
					if(++countY >= 4) {
						return false;
					}
				}
				now = bit;
			}
		}
		
		int[] visited = new int[1];
		dfs(visited, select, now);
		if (visited[0] == select) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void dfs(int[] visited, int select, int now) {
		if (now >= 5) {
			if ((select & (1 << (now - 5))) != 0){
				if ((visited[0] & (1 << (now - 5))) == 0) {
					visited[0] |= 1 << (now - 5);
					dfs(visited, select, now - 5);
				}
			}
		}
		if (now < 20) {
			if ((select & (1 << (now + 5))) != 0){
				if ((visited[0] & (1 << (now + 5))) == 0) {
					visited[0] |= 1 << (now + 5);
					dfs(visited, select, now + 5);
				}
			}
		}
		if (now % 5 != 0) {
			if ((select & (1 << (now - 1))) != 0){
				if ((visited[0] & (1 << (now - 1))) == 0) {
					visited[0] |= 1 << (now - 1);
					dfs(visited, select, now - 1);
				}
			}
		}
		if (now % 5 != 4) {
			if ((select & (1 << (now + 1))) != 0){
				if ((visited[0] & (1 << (now + 1))) == 0) {
					visited[0] |= 1 << (now + 1);
					dfs(visited, select, now + 1);
				}
			}
		}
	}
}