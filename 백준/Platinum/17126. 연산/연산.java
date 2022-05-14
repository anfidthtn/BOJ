import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static class Query {
		int op;
		String key1;
		String key2;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Set<String> keySet = new TreeSet<>();
		Query[] querys = new Query[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			querys[i] = new Query();
			querys[i].op = Integer.parseInt(st.nextToken());
			switch (querys[i].op) {
			case 1:
				// 더하기
				querys[i].key1 = st.nextToken();
				keySet.add(querys[i].key1);
				// value는 키셋에 안 넣음
				querys[i].key2 = st.nextToken();
				break;
			case 2:
				// 구간합
				querys[i].key1 = st.nextToken();
				keySet.add(querys[i].key1);
				querys[i].key2 = st.nextToken();
				keySet.add(querys[i].key2);
				break;
			case 3:
				// 삭제
				querys[i].key1 = st.nextToken();
				keySet.add(querys[i].key1);
				break;
			}
		}
		List<String> keyList = new ArrayList<>(keySet);
		keyList.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() != o2.length()) {
					return o1.length() - o2.length();
				}
				return o1.compareTo(o2);
			}
		});

		int treeSize = keyList.size();
		long[] segTree = new long[treeSize * 4];
		long[] nowNum = new long[treeSize];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int key1 = binarySearch(keyList, 0, treeSize - 1, querys[i].key1);
			switch (querys[i].op) {
			case 1:
				int value = Integer.parseInt(querys[i].key2);
				update(segTree, 1, 0, treeSize - 1, key1, value);
				nowNum[key1] += value;
				sb.append(segTree[1]).append(" ");
				break;
			case 2:
				int key2 = binarySearch(keyList, 0, treeSize - 1, querys[i].key2);
				sb.append(getSum(segTree, 1, 0, treeSize - 1, key1, key2)).append(" ");
				break;
			case 3:
				update(segTree, 1, 0, treeSize - 1, key1, -nowNum[key1]);
				nowNum[key1] = 0;
				sb.append(segTree[1]).append(" ");
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
	}

	public static long getSum(long[] segTree, int nodeNum, int left, int right, int start, int end) {
		if (end < left || right < start) {
			return 0;
		}
		if (start <= left && right <= end) {
			return segTree[nodeNum];
		}
		int mid = (left + right) >> 1;
		return getSum(segTree, nodeNum * 2, left, mid, start, end)
				+ getSum(segTree, nodeNum * 2 + 1, mid + 1, right, start, end);
	}

	public static void update(long[] segTree, int nodeNum, int left, int right, int targetIdx, long value) {
		if (targetIdx < left || right < targetIdx) {
			return;
		}
		segTree[nodeNum] += value;
		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		update(segTree, nodeNum * 2, left, mid, targetIdx, value);
		update(segTree, nodeNum * 2 + 1, mid + 1, right, targetIdx, value);
	}

	public static int binarySearch(List<String> keyList, int left, int right, String target) {
		int mid = (left + right) >> 1;
		int comp = stringCompare(keyList.get(mid), target);
		if (comp == 0) {
			return mid;
		} else if (comp > 0) {
			return binarySearch(keyList, left, mid, target);
		} else {
			return binarySearch(keyList, mid + 1, right, target);
		}
	}

	public static int stringCompare(String o1, String o2) {
		if (o1.length() != o2.length()) {
			return o1.length() - o2.length();
		}
		return o1.compareTo(o2);
	}
}