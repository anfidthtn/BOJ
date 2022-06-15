import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Icecream{
		int num;
		int amount;
		boolean status;
		public Icecream(int num, int amount) {
			this.num = num;
			this.amount = amount;
			this.status = true;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		PriorityQueue<Icecream> leftPQ = new PriorityQueue<>(new Comparator<Icecream>() {
			@Override
			public int compare(Icecream o1, Icecream o2) {
				if (o1.amount == o2.amount) {
					return o1.num - o2.num;
				}
				else {
					return o2.amount - o1.amount;
				}
			}
		});
		PriorityQueue<Icecream> rightPQ = new PriorityQueue<>(new Comparator<Icecream>() {
			@Override
			public int compare(Icecream o1, Icecream o2) {
				if (o1.amount == o2.amount) {
					return o2.num - o1.num;
				}
				else {
					return o2.amount - o1.amount;
				}
			}
		});
		for(int i = 1; i <= N; i++) {
			Icecream now = new Icecream(i, Integer.parseInt(st.nextToken()));
			leftPQ.add(now);
			rightPQ.add(now);
		}
		PriorityQueue<Icecream>[] pqs = new PriorityQueue[2];
		pqs[0] = leftPQ;
		pqs[1] = rightPQ;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int nowQ = 0;
		for(int i = 0; i < M; i++) {
			while(!pqs[nowQ].peek().status) {
				pqs[nowQ].poll();
			}
			Icecream temp = pqs[nowQ].poll();
			temp.status = false;
			bw.write(String.valueOf(temp.num));
			bw.write("\n");
			if (temp.amount % 7 == 0) {
				nowQ ^= 1;
			}
		}
		bw.flush();
	}
}