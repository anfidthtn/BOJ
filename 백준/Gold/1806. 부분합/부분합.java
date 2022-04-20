import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		st.nextToken();
		int S = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Queue<Integer> queue = new LinkedList<>();
		int minSize = Integer.MAX_VALUE;
		int sum = 0;
		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			queue.add(num);
			sum += num;
			if (sum >= S) {
				while(!queue.isEmpty() && sum >= S) {
					minSize = Math.min(minSize, queue.size());
					sum -= queue.poll();
				}
			}
		}
		if (minSize == Integer.MAX_VALUE) System.out.print(0);
		else System.out.print(minSize);
	}
}