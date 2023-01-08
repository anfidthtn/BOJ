import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static class Info {
		int step;
		long x, y;

		public Info(int step, long x, long y) {
			this.step = step;
			this.x = x;
			this.y = y;
		}
	}

	// 정답 문자열 출력용
	static StringBuilder sb;
	static int K, M;
	// 현 시점이 몇 번째 턴인지, 어느 방향인지
	static int step, dir;
	// 현 시점의 위치
	static long x, y;
	// 현 시점의 넘버
	static int num;
	// 정보를 저장해둘 맵
	// 어차피 dig(x)가 0~9이니, * M 해서 저장해도 개수는 그대로 10개, 방향 4개 까지 해도 총 40개라 Map에 저장
	static Map<Integer, Info> infos;
	// 북, 동, 남, 서 방향별 변화량
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	// 테케가 여러개이기 때문에 미리 나올 수 있는 범위를 미리 구해두기
	static int[] nextNums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// dig(x)가 최대 9, M이 최대 1000이라서 0 ~ 9000까지만 미리 구해주면 된다.
		nextNums = new int[10000];
		Arrays.fill(nextNums, -1);
		for (int i = 0; i < 10000; i++) {
			setNum(i);
		}
		int t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			solve();
		}
		System.out.print(sb.toString());
	}

	public static int setNum(int num) {
		if (nextNums[num] >= 0) {
			return nextNums[num];
		}
		if (num < 10) {
			return nextNums[num] = num;
		}
		int res = 0;
		int n = num;
		while (n > 0) {
			res += n % 10;
			n /= 10;
		}
		return nextNums[num] = setNum(res);
	}

	public static void solve() {
		// 저장정보 갱신
		infos = new TreeMap<>();
		step = 0;
		dir = 0;
		x = 0;
		y = 0;
		num = 1;
		// K를 하나씩 줄여나가며 현재 위치와 보는 방향을 저장
		while (K > 0) {
			// 한 단계 진행
			step++;
			x += dx[dir] * num;
			y += dy[dir] * num;
			dir += 1;
			dir &= 3;
			num *= M;
			num = nextNums[num];
			K--;
			// 진행 후의 방향, 골드 넘버 상태가 똑같은 경우가 있는지 확인
			// * 10000은 num이 최대 9000인 것을 이용해 방향과 골드 넘버 정보를 한 Integer에 담기 위해 사용
			if (infos.containsKey(dir * 10000 + num)) {
				/*
				 * 예를 들어 1턴에 40, 40에 있고 골드넘버 100에 오른쪽을 보고있었고
				 * 25턴에 100, 50에 있고 골드넘버 100에 오른쪽을 보고있다면
				 * 49턴에 160, 60에 있고 골드넘버 100에 오른쪽을 보고있을 것을 알 수 있다.
				 * 이 방법으로 몇 턴 지났는지를 term으로 구하고
				 * 남은 K값으로 몇 term을 진행시킬 수 있는지 cycle을 구해서
				 * 해당 변화량만큼 x, y를 한 번에 변화시킨다.
				 */
				Info before = infos.get(dir * 10000 + num);
				int term = step - before.step;
				int cycle = K / term;
				x += cycle * (x - before.x);
				y += cycle * (y - before.y);
				K %= term;
				break;
			} else {
				infos.put(dir * 10000 + num, new Info(step, x, y));
			}
		}
		while (K > 0) {
			// term을 찾아 한 번에 변화시킨 경우 K가 조금 남을 수 있으므로 남은 횟수를 조금 진행한다.
			x += dx[dir] * num;
			y += dy[dir] * num;
			dir += 1;
			dir &= 3;
			num *= M;
			num = nextNums[num];
			K--;
		}
		sb.append(x).append(" ").append(y).append("\n");
	}
}