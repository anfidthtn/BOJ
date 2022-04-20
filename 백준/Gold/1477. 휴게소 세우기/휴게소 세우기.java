import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		st.nextToken();
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		ArrayList<Integer> temp = new ArrayList<>();
		while (st.hasMoreTokens()) {
			temp.add(Integer.parseInt(st.nextToken()));
		}
		temp.add(0);
		temp.add(L);
		temp.sort((a, b) -> a < b ? -1 : 1);
		
		// [구간길이, 구간의 휴게소 개수] 로 구간별 현재 길이 최대값이 위로 오게 정렬되는 우선순위 큐를 만든다.
		PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> Math.ceil((double) a[0] / a[1]) < Math.ceil((double) b[0] / b[1]) ? 1 : -1);
		for (int i = 0; i < temp.size() - 1; i++) {
			pq.add(new Integer[] {temp.get(i + 1) - temp.get(i), 1});
		}
		for (int i = 0; i < M; i++) {
			Integer[] now = pq.poll();
			now[1]++;
			pq.add(now);
		}
		System.out.print((int)Math.ceil((double) pq.peek()[0] / pq.peek()[1]));
	}
}