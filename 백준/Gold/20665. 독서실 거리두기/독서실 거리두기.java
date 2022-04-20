import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Book {
		int start;
		int end;
		public Book(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
	}
	
	static class Use{
		int seatNum;
		int endTime;
		public Use(int seatNum, int endTime) {
			super();
			this.seatNum = seatNum;
			this.endTime = endTime;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		PriorityQueue<Book> books = new PriorityQueue<>(new Comparator<Book>() {
			@Override
			public int compare(Book o1, Book o2) {
				if (o1.start == o2.start) {
					// 시작 시간이 같으면 이용 시간 짧은 쪽이 먼저
					return o1.end - o2.end;
				}
				// 시작 시간 빠른쪽이 먼저
				return o1.start - o2.start;
			}
		});
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int start = lineTime(Integer.parseInt(st.nextToken()));
			int end = lineTime(Integer.parseInt(st.nextToken()));
			if (start == end) continue;
			books.add(new Book(start + 1, end));
		}
		
		boolean[] isUsed = new boolean[N + 1];
		
		PriorityQueue<Use> using = new PriorityQueue<>(new Comparator<Use>() {
			@Override
			public int compare(Use o1, Use o2) {
				// endTime기준 정렬만 하면 된다.
				return o1.endTime - o2.endTime;
			}
		});
		
		// 현재 사용중인 좌석 수
		int capacity = 0;
		// 타겟 사용가능 수
		int count = 0;
		for(int time = 541; time <= 1260; time++) {
			if (capacity == 0) {
				if(!books.isEmpty() && books.peek().start == time) {
					isUsed[1] = true;
					capacity++;
					using.add(new Use(1, books.poll().end));
				}
			}
			if (capacity > 0) {
				while(!books.isEmpty() && books.peek().start == time) {
					int nextSeat = getNextSeat(isUsed, N);
					isUsed[nextSeat] = true;
					capacity++;
					using.add(new Use(nextSeat, books.poll().end));
				}
			}
			if (!isUsed[P]) {
				count++;
			}
			if (capacity > 0) {
				while(!using.isEmpty() && using.peek().endTime <= time) {
					isUsed[using.poll().seatNum] = false;
					capacity--;
				}
			}
		}
		System.out.println(count);
	}
	public static int lineTime(int time) {
		return (time / 100) * 60 + time % 100;
	}
	public static int getNextSeat(boolean[] isUsed, int N) {
		int maxDistance = 0;
		int mdIdx = 0;
		for(int seatNum = 1; seatNum <= N; seatNum++) {
			if (isUsed[seatNum]) continue;
			int left = seatNum;
			int right = seatNum;
			while(1 <= left) {
				if (isUsed[left]) break;
				left--;
			}
			while(right <= N) {
				if (isUsed[right]) break;
				right++;
			}
			if (left == 0) left = -300;
			if (right == N + 1) right = 300;
			int distance = Math.min(seatNum - left, right - seatNum);
			if (maxDistance < distance) {
				mdIdx = seatNum;
				maxDistance = distance;
			}
		}
		return mdIdx;
	}
}