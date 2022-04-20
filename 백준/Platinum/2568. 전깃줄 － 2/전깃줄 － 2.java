import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 왼쪽 -> 오른쪽 연결 관계를 저장하는 어레이리스트
		ArrayList<Integer[]> lines = new ArrayList<>();
		// 입력을 받아서 저장한다.
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lines.add(new Integer[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
		}
		// 왼쪽을 기준으로 정렬한다.
		lines.sort((a, b) -> a[0] < b[0] ? -1 : 1);
		int[] point = new int[n];

		// 인덱스 개수만큼 연결할 때 최저값이다.
		ArrayList<Integer> minNums = new ArrayList<>();
		// 0을 넣어준다.
		minNums.add(0);
		for(int i = 0; i < lines.size(); i++) {
			Integer[] line = lines.get(i);
			int link = line[1];
			int insertIdx = 1 + getBinaryLowerBoundIdx(minNums, link, 0, minNums.size() - 1);
			point[i] = insertIdx;
			if (insertIdx >= minNums.size()) {
				minNums.add(link);
			}
			else {
				minNums.set(insertIdx, Math.min(minNums.get(insertIdx), link));
			}
		}
		Set<Integer> resultExclusive = new HashSet<>();
		int targetLen = minNums.size() - 1;
		for(int i = n - 1; i >= 0; i--) {
			if (point[i] == targetLen) {
				targetLen--;
				resultExclusive.add(lines.get(i)[0]);
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(n - resultExclusive.size()).append('\n');
		for(int i = 0; i < n; i++) {
			if (resultExclusive.contains(lines.get(i)[0])) continue;
			sb.append(lines.get(i)[0]).append('\n');
		}
		System.out.print(sb);
	}

	// 이진탐색 하면서 lowerBound찾는 메소드
	public static int getBinaryLowerBoundIdx(ArrayList<Integer> minNums, int target, int left, int right) {
		if (right <= left)
			return right;
		int mid = (left + right + 1) >> 1;
		if (minNums.get(mid) < target)
			return getBinaryLowerBoundIdx(minNums, target, mid, right);
		else
			return getBinaryLowerBoundIdx(minNums, target, left, mid - 1);
	}
}