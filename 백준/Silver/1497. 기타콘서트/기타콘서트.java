import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static String[][] infos;
	static int[] guitCounts;
	static int answer;
	static int maxSong;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		infos = new String[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			infos[i][0] = st.nextToken();
			infos[i][1] = st.nextToken();
		}
		answer = 1 << 30;
		maxSong = 0;
		guitCounts = new int[M];
		check(0, 0);
		if (maxSong == 0) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);
		}
	}
	public static void check(int idx, int count) {
		int temp = 0;
		for(int i = 0; i < M; i++) {
			if (guitCounts[i] > 0) {
				temp++;
			}
		}
		if (temp > maxSong) {
			answer = count;
			maxSong = temp;
		}
		else if (temp == maxSong) {
			answer = Math.min(answer, count);
		}
		if (idx == N) {
			return;
		}
		for(int i = 0; i < M; i++) {
			if (infos[idx][1].charAt(i) == 'Y') {
				guitCounts[i]++;
			}
		}
		check(idx + 1, count + 1);
		for(int i = 0; i < M; i++) {
			if (infos[idx][1].charAt(i) == 'Y') {
				guitCounts[i]--;
			}
		}
		check(idx + 1, count);
	}
}