import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

class Info{
	int location;
	int time;
	public Info(int location, int time) {
		super();
		this.location = location;
		this.time = time;
	}
}

public class Main {
	public static void find(Info info, int K) {
		if (info.location == K) {
			System.out.print(info.time);
			System.exit(0);
		}
	}
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int K = s.nextInt();
		
		Deque<Info> deque = new LinkedList<>();
		Set<Integer> isVisited = new HashSet<>();
		deque.offer(new Info(N, 0));
		isVisited.add(N);
		while (!deque.isEmpty()) {
			Info nowInfo = deque.poll();
			find(nowInfo, K);
			int nextLocation = nowInfo.location * 2;
			if (!isVisited.contains(nextLocation) && nextLocation <= 100000 && nowInfo.location > 0) {
				deque.offerFirst(new Info(nowInfo.location * 2, nowInfo.time));
				isVisited.add(nextLocation);
			}
			nextLocation = nowInfo.location - 1;
			if (nextLocation >= 0) {
				if (!isVisited.contains(nextLocation) && !(nowInfo.location == 1 && K != 0)) {
					deque.offer(new Info(nowInfo.location - 1, nowInfo.time + 1));
					isVisited.add(nextLocation);
				}
			}
			nextLocation = nowInfo.location + 1;
			if (nextLocation <= 100000) {
				if (!isVisited.contains(nextLocation) && !(nowInfo.location == 99999 && K != 100000)) {
					deque.offer(new Info(nowInfo.location + 1, nowInfo.time + 1));
					isVisited.add(nextLocation);
				}
			}
		}
		System.out.print("여기오면에러야");
	}
}