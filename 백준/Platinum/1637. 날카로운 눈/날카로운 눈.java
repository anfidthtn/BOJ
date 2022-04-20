import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] infos = new int[N][3];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			infos[i][0] = Integer.parseInt(st.nextToken());
			infos[i][1] = Integer.parseInt(st.nextToken());
			infos[i][2] = Integer.parseInt(st.nextToken());
		}
		long target = search(infos, 1l, 2147483648l);
		if (target == 2147483648l) {
			System.out.print("NOTHING");
		}
		else {
			int result = 0;
			for(int i = 0; i < N; i++) {
				if (target < infos[i][0] || target > infos[i][1]) continue;
				if ((target - infos[i][0]) % infos[i][2] == 0) result++;
			}
			System.out.print(target + " " + result);
		}
	}
	public static long search(int[][] infos, long left, long right) {
		if (right <= left) return left;
		long mid = (left + right) >> 1;
		long lessSum = 0;
		int N = infos.length;
		for(int i = 0; i < N; i++) {
			lessSum += countInfo(infos[i], mid);
		}
		if ((lessSum & 1) == 1) {
			return search(infos, left, mid);
		}
		else {
			return search(infos, mid + 1, right);
		}
	}
	
	// 타겟 이하의 정수가 몇 개 있는지 반환하는 메소드
	public static int countInfo(int[] info, long target) {
		// 목표가 시작지점보다도 작으면 0개
		if (target < info[0]) return 0;
		// 끝지점 < 시작지점 이면 0개
		if (info[1] < info[0]) return 0;
		// 목표지점이 끝지점보다 크면 그냥 목표지점을 끝지점으로 만들어서 계산을 한 번에 하도록 유도
		if (info[1] < target) target = info[1];
		return (int) (1 + (target - info[0]) / info[2]);
	}
}