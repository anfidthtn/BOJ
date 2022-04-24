import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		M = Integer.parseInt(st.nextToken());
		
		Set<Integer> numSet = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			numSet.add(Integer.parseInt(st.nextToken()));
		}
		
		List<Integer> numList = new ArrayList<>(numSet);
		numList.sort(Integer::compareTo);
		N = numList.size();
		
		StringBuilder sb = new StringBuilder();
		int[] selected = new int[M];
		makePermu(sb, numList, 0, 0, selected);
		System.out.println(sb.toString());
	}
	public static void makePermu(StringBuilder sb, List<Integer> numList, int count, int idx, int[] selected) {
		if (count == M) {
			for(int i = 0; i < M; i++) {
				sb.append(numList.get(selected[i])).append(" ");
			}
			sb.append("\n");
			return;
		}
		if (N <= idx) {
			return;
		}
		selected[count] = idx;
		makePermu(sb, numList, count + 1, idx, selected);
		makePermu(sb, numList, count, idx + 1, selected);
	}
}