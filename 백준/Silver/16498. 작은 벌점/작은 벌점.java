import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Card{
		int num;
		int who;
		public Card(int num, int who) {
			this.num = num;
			this.who = who;
		}
	}
	static final int DEFAULTVALUE = 1_000_000_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		List<Card> cards = new ArrayList<>(A + B + C);
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < A; i++) {
			cards.add(new Card(Integer.parseInt(st.nextToken()), 0));
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < B; i++) {
			cards.add(new Card(Integer.parseInt(st.nextToken()), 1));
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			cards.add(new Card(Integer.parseInt(st.nextToken()), 2));
		}
		cards.sort(new Comparator<Card>() {
			@Override
			public int compare(Card o1, Card o2) {
				return o1.num - o2.num;
			}
		});
		
		int answer = 1_000_000_000;
		int[] now = new int[3];
		now[0] = DEFAULTVALUE;
		now[1] = DEFAULTVALUE;
		now[2] = DEFAULTVALUE;
		
		for(int i = 0; i < A + B + C; i++) {
			now[cards.get(i).who] = cards.get(i).num;
			answer = Math.min(answer, getMin(now));
		}
		System.out.println(answer);
	}
	public static int getMin(int[] now) {
		for(int i = 0; i < 3; i++) {
			if (now[i] == DEFAULTVALUE) {
				return DEFAULTVALUE;
			}
		}
		return Math.max(Math.max(now[0], now[1]), now[2]) - Math.min(Math.min(now[0], now[1]), now[2]);
	}
}