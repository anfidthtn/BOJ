import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static boolean[] notprime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int firstNum = Integer.parseInt(st.nextToken());
		List<Integer>[] numList = new ArrayList[2];
		numList[0] = new ArrayList<>();
		numList[1] = new ArrayList<>();
		numList[firstNum % 2].add(firstNum);
		for (int i = 2; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			numList[num % 2].add(num);
		}
		if (numList[0].size() != numList[1].size()) {
			System.out.println(-1);
			return;
		}
		if (firstNum % 2 == 1) {
			List<Integer> temp = numList[0];
			numList[0] = numList[1];
			numList[1] = temp;
		}
		notprime = new boolean[2000];
		// notprime[0] = true;
		// notprime[1] = true;
		for (int i = 2; i < 2000; i++) {
			if (notprime[i]) {
				continue;
			}
			for (int notp = i * 2; notp < 2000; notp += i) {
				notprime[notp] = true;
			}
		}
		int listSize = numList[0].size();
		List<Integer>[] link = new ArrayList[listSize];
		link[0] = new ArrayList<>();
		for (int i = 1; i < listSize; i++) {
			link[i] = new ArrayList<>();
			for (int j = 0; j < listSize; j++) {
				if (!notprime[numList[0].get(i) + numList[1].get(j)]) {
					link[i].add(j);
				}
			}
		}
		List<Integer> result = new ArrayList<>();
		for (int j = 0; j < listSize; j++) {
			if (notprime[numList[0].get(0) + numList[1].get(j)]) {
				continue;
			}
			int[] select = new int[listSize];
			for(int i = 0; i < listSize; i++) {
				select[i] = -1;
			}
			select[j] = 0;
			int count = 1;
			for(int i = 1; i < listSize; i++) {
				boolean[] visited = new boolean[listSize];
				visited[j] = true;
				if (matching(link, i, select, visited)) {
					count++;
				}
				else {
					break;
				}
			}
			if (count == listSize) {
				result.add(numList[1].get(j));
			}
		}
		if(result.size() == 0) {
			System.out.print(-1);
		}
		else {
			result.sort(Integer::compareTo);
			StringBuilder sb = new StringBuilder();
			for(int res : result) {
				sb.append(res).append(" ");
			}
			System.out.print(sb.toString());
		}
	}
	public static boolean matching(List<Integer>[] link, int now, int[] select, boolean[] visited) {
		for(int next : link[now]) {
			if(visited[next]) {
				continue;
			}
			visited[next] = true;
			if(select[next] == -1 || matching(link, select[next], select, visited)) {
				select[next] = now;
				return true;
			}
		}
		return false;
	}
}