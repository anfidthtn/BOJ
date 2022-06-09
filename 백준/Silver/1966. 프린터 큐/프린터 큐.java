import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Docu{
		int num;
		int prio;
		public Docu(int num, int prio) {
			this.num = num;
			this.prio = prio;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int maxprio = 1;
			boolean[] isMax = new boolean[10];
			int[] counts = new int[10];
			isMax[1] = true;
			Queue<Docu> queue = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				int prio = Integer.parseInt(st.nextToken());
				counts[prio]++;
				if(prio > maxprio) {
					isMax[maxprio] = false;
					maxprio = prio;
					isMax[maxprio] = true;
				}
				queue.add(new Docu(i, prio));
			}
			int count = 0;
			while(!queue.isEmpty()) {
				if (!isMax[queue.peek().prio]) {
					queue.add(queue.poll());
					continue;
				}
				Docu now = queue.poll();
				count++;
				if (now.num == M) {
					break;
				}
				if(--counts[now.prio] == 0) {
					isMax[now.prio] = false; 
					for(int i = now.prio - 1; i >= 0; i--) {
						if (counts[i] > 0) {
							isMax[i] = true;
							break;
						}
					}
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}
}