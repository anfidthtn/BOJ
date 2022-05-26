import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int count;
		Node[] next;
		public Node() {
			this.count = 0;
			this.next = new Node[2];
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Node start = new Node();
		StringBuilder sb = new StringBuilder();
		int maxBit = 29;
		for(int i = 0; i <= M; i++) {
			int q;
			int num;
			if (i == 0) {
				q = 1;
				num = 0;
			}
			else {
				st = new StringTokenizer(br.readLine());
				q = Integer.parseInt(st.nextToken());
				num = Integer.parseInt(st.nextToken());				
			}
			Node nowNode = start;
			switch(q) {
			case 1:
				for(int bit = maxBit; bit >= 0; bit--) {
					int nowbit = (num & (1 << bit)) >> bit;
					if (nowNode.next[nowbit] == null) {
						nowNode.next[nowbit] = new Node();
					}
					nowNode = nowNode.next[nowbit];
					nowNode.count++;
				}
				break;
			case 2:
				for(int bit = maxBit; bit >= 0; bit--) {
					int nowbit = (num & (1 << bit)) >> bit;
					nowNode = nowNode.next[nowbit];
					nowNode.count--;
				}
				break;
			case 3:
				int xorNum = 0;
				for(int bit = maxBit; bit >= 0; bit--) {
					int nowbit = (num & (1 << bit)) >> bit;
					if (nowNode.next[nowbit] == null) {
						nowNode.next[nowbit] = new Node();
					}
					if (nowNode.next[nowbit ^ 1] == null) {
						nowNode.next[nowbit ^ 1] = new Node();
					}
					if (nowNode.next[nowbit ^ 1].count > 0) {
						xorNum += (nowbit ^ 1) << bit;
						nowNode = nowNode.next[nowbit ^ 1];
					}
					else if (nowNode.next[nowbit].count > 0){	
						xorNum += nowbit << bit;					
						nowNode = nowNode.next[nowbit];
					}
					else {
						break;
					}
				}
				sb.append(num ^ xorNum).append("\n");
				break;
			}
		}
		System.out.print(sb.toString());
	}
}