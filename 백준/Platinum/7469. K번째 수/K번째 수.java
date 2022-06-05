import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] segTree;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		segTree = new ArrayList[4 * N];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			insertNum(1, 1, N, Integer.parseInt(st.nextToken()) + 1_000_000_000, i);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(getCountLowerValue(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1, 2_000_000_002, Integer.parseInt(st.nextToken())) - 1_000_000_001).append("\n");
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
	}
	public static int getCountLowerValue(int start, int end, int low, int high, int K) {
		if (high <= low) {
			return high;
		}
		int mid = (int)(((long)low + (long)high) / 2);
		if (K <= getCount(1, 1, N, start, end, mid)) {
			return getCountLowerValue(start, end, low, mid, K);
		}
		else {
			return getCountLowerValue(start, end, mid + 1, high, K);
		}
	}
	public static int getCount(int nodeNum, int left, int right, int start, int end, int testValue) {
		if (end < left || right < start) {
			return 0;
		}
		if (start <= left && right <= end) {
			return getLowerCount(segTree[nodeNum], 0, segTree[nodeNum].size(), testValue);
		}
		int mid = (left + right) >> 1;
		return getCount(nodeNum * 2, left, mid, start, end, testValue)
				+ getCount(nodeNum * 2 + 1, mid + 1, right, start, end, testValue);
	}
	public static int getLowerCount(List<Integer> list, int left, int right, int testValue) {
		if (right <= left) {
			return right;
		}
		int mid = (left + right) >> 1;
		if (testValue <= list.get(mid)) {
			return getLowerCount(list, left, mid, testValue);
		}
		else {
			return getLowerCount(list, mid + 1, right, testValue);
		}
	}
	public static void insertNum(int nodeNum, int left, int right, int value, int targetIdx) {
		if (targetIdx < left || right < targetIdx) {
			return;
		}
		if (segTree[nodeNum] == null) {
			segTree[nodeNum] = new ArrayList<>();
		}
		segTree[nodeNum].add(value);
		if (left == right) {
			return;
		}
		if (right == targetIdx) {
			segTree[nodeNum].sort(Integer::compareTo);
		}
		int mid = (left + right) >> 1;
		insertNum(nodeNum * 2, left, mid, value, targetIdx);
		insertNum(nodeNum * 2 + 1, mid + 1, right, value, targetIdx);
	}
}