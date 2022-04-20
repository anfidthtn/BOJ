import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Room implements Comparable<Room> {
		// 강의실 번호
		int roomNum;
		// 해당 강의실에서 마지막으로 진행될 강의의 끝 시간
		int endTime;

		public Room(int roomNum, int endTime) {
			super();
			this.roomNum = roomNum;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Room o) {
			// 정렬은 강의 끝 시간 순으로 오름차순
			if (endTime < o.endTime)
				return -1;
			if (endTime > o.endTime)
				return 1;
			return 0;
		}
	}

	static class Lecture implements Comparable<Lecture> {
		// 강의 번호
		int lectNum;
		// 강의 시작 시간
		int startTime;
		// 강의 종료 시간
		int endTime;

		public Lecture(int lectNum, int startTime, int endTime) {
			super();
			this.lectNum = lectNum;
			this.startTime = startTime;
			this.endTime = endTime;
		}

		@Override
		public int compareTo(Lecture o) {
			// 강의는 시작 시간 오름차순 정렬한다.
			if (startTime < o.startTime)
				return -1;
			if (startTime > o.startTime)
				return 1;
			return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// N개의 강의를 입력받는다.
		Lecture[] lectures = new Lecture[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 강의 번호는 인덱스화를 해야해서 -1해서 받는다.
			lectures[i] = new Lecture(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		// 입력받은 강의를 시작시간 순으로 정렬한다.
		Arrays.sort(lectures);

		// N개의 강의별로 배정받은 강의실의 번호를 저장할 곳이다.
		int[] roomNum = new int[N];

		
		// 강의실은 강의가 끝나는 시간이 앞쪽인게 먼저 나오도록 PQ에 넣는다.
		// 즉 PQ의 가장 앞의 강의 끝 시간을 보면 모든 강의실 중 가장 먼저 강의가 끝나는 시간을 보여준다.
		int newRoomNum = 1;
		PriorityQueue<Room> roomWaiting = new PriorityQueue<>();
		roomWaiting.add(new Room(newRoomNum++, 0));
		
		// N개의 강의를 배정해야한다.
		for(int i = 0; i < N; i++) {
			if (lectures[i].startTime < roomWaiting.peek().endTime) {
				// 만약 현재 모든 강의실에서의 마지막 강의가 끝나는 시간보다 새 강의의 시간이 먼저라면
				// 새로운 강의실을 만들어서 배정받아야한다.
				
				// 새로 배정받을 강의실 번호를 강의의 강의실번호로 저장
				roomNum[lectures[i].lectNum] = newRoomNum;
				// 새 번호로 강의실을 만들고, 해당 강의의 끝 시간을 강의실의 마지막 강의 끝 시간으로 등록해서 큐에 넣는다.
				roomWaiting.add(new Room(newRoomNum++, lectures[i].endTime));
			}
			else {
				// 강의 시간에 맞춰 끝나거나 그 전에 끝난 강의실이 있다면
				// 기존 강의실에 배정하면 된다.
				Room now = roomWaiting.poll();
				// 기존 강의실 번호로 저장
				roomNum[lectures[i].lectNum] = now.roomNum;
				// 기존 강의실 마지막 강의 끝 시간을 변경
				now.endTime = lectures[i].endTime;
				// 다시 큐에 넣기
				roomWaiting.add(now);
			}
		}
		System.out.println(newRoomNum - 1);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(roomNum[i]).append('\n');
		}
		System.out.print(sb);
	}
}