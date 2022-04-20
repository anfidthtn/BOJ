import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			/**
			 * 공통적으로 인덱스 0은 편의상 버린다.
			 * link[i] : i번째 학생은 link[i]번째 학생과 팀이 되고싶어한다.
			 * isVisited[i] : i번째 학생에 대한 처리가 이미 끝난 상태이다.
			 * cycleNum[i] : 그룹화에 대한 검사 시, i번째 학생이 몇 번째로 방문된 학생인지 임시저장한다.
			 */
			int[] link = new int[n + 1];
			boolean[] isVisited = new boolean[n + 1];
			int[] cycleNum = new int[n + 1];
			for(int i = 1; i <= n; i++) {
				link[i] = Integer.parseInt(st.nextToken());
			}
			// 그룹화가 되지 않은 학생 수를 셀 변수 count이다.
			int count = n;
			for(int i = 1; i <= n; i++) {
				// 처리된 녀석은 넘어간다.
				if (isVisited[i]) continue;
				// i번째 녀석에 대해 처리를 했다는 것
				isVisited[i] = true;
				// i번째 녀석을 1번으로 하여 탐색을 시작한다.
				cycleNum[i] = 1;
				// 그룹화된 학생 수를 반환받아 빼준다.
				count -= getGroupStCount(link, isVisited, cycleNum, i, 2);
				// 탐색 후에는 임시번호를 없애준다.
				cycleNum[i] = 0;
			}
			sb.append(count).append('\n');
		}
		System.out.print(sb.toString());
	}
	public static int getGroupStCount(int[] link, boolean[] isVisited, int[] cycleNum, int nowSt, int nowNum) {
		// 탐색 번호의 학생이 원하는 학생에 대한 정보를 본다.
		int nextSt = link[nowSt];
		// cycleNum[nextSt] > 0 이란 소리는 nextSt번째 학생이 원하는 학생을 따라가다 보니 nowSt번째 학생이 나왔단 소리이다.
		if (cycleNum[nextSt] > 0) {
			// 그러면 nextSt번째 학생 -> nowSt번째 학생을 포함하여 그 구간에 있던 학생 수만큼이 그룹화가 될 수 있으므로 그 수를 반환한다.
			return nowNum - cycleNum[nextSt];
		}
		// 0이라는 소리는 이번 탐색과정에서 nextSt는 nowSt까지 탐색해 오는 데에 발견되지 않은 학생이란 소리이다.
		// 발견되지 않은 학생인데 isVisited가 찍혀있다는 것은, nextSt -> nowSt의 연결관계가 성립하지 않는다는 소리이므로 0을 반환한다.
		if (isVisited[nextSt]) return 0;
		// 등록해주고
		isVisited[nextSt] = true;
		// 이번 탐색에서 몇 번째로 방문한 학생인지 저장해주고
		cycleNum[nextSt] = nowNum;
		// 재귀적으로 그룹화된 학생 수를 조사한다.
		int stCount = getGroupStCount(link, isVisited, cycleNum, nextSt, nowNum + 1);
		// 학생 수를 전부 조사했다면 이번 탐색의 번호를 지워준다.
		cycleNum[nextSt] = 0;
		// 그룹화된 학생 수 반환.
		return stCount;
	}
}