import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, S, Q;

	static class Info {
		/*
		 * num : 얘가 몇 번 참가자인지
		 * solved : 얘가 몇 번째 문제를 풀었는지
		 * solIdx : 얘가 해당 solved 수 유저를 모아놓은 그룹에서 몇 번째 idx인지
		 * valid : 이 객체가 유효한지
		 */
		int num, solved, solIdx;
		boolean valid;

		public Info(int num, int solved) {
			this.num = num;
			this.solved = solved;
			this.valid = true;
		}
	}

	static Info[] infos; // 1 ~ N번 유저 현재정보 보관할 배열
	static List<Info>[] solvedList; // 0 ~ 500000문제 solved한 유저 순서대로 보관할 List의 배열
	static int[] 변수이름뭐로하지; // 해당 문제를 1번보다 더 빨리 푼 사람 수
	static int cut_solved, cut_idx; // S등 커트라인이 몇 문제 solved에 몇 번째 idx까지인지
	static int 자기보다더많은문제푼사람수; // 나중에 생각나면 하자
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		infos = new Info[N + 1];
		solvedList = new List[Q + 1];
		변수이름뭐로하지 = new int[Q + 1];
		for (int i = 0; i <= Q; i++) {
			solvedList[i] = new ArrayList<>();
		}
		for (int i = N; i >= 1; i--) {
			Info temp = new Info(i, 0); // 0문제의 경우 N번 ~ 1번 순서로 푼 것으로 간주하여 1번 유저가 꼴등으로 인식되게 함
			solvedList[0].add(temp); // 0문제 푼 곳에 해당 유저를 넣음
			temp.solIdx = solvedList[0].size() - 1; // 해당 유저가 들어간 곳을 저장함.
			infos[i] = temp;
		}
		cut_solved = 0; // 초기 등급컷은 0문제
		cut_idx = S - 1; // 초기 0문제들 사이에서 idx 컷은 S - 1인덱스 (S번째)
		변수이름뭐로하지[0] = N - 1; // 초기 0문제를 1번유저보다 빨리 푼 사람 수는 N - 1명이다.
		자기보다더많은문제푼사람수 = 0; // 초기에 전부 0문제이므로 자기보다 더 많은 문제를 푼 사람 수는 0명이다.
		for (int q = 0; q < Q; q++) {
			check(Integer.parseInt(br.readLine()));
			checkNeeds();
		}
		System.out.print(sb);
	}

	public static void check(int userNum) {
		switch (userNum) {
		case 1:
			checkFirst();
			break;
		default:
			checkOther(userNum);
			break;
		}
	}

	public static void checkNeeds() {
		int me = 자기보다더많은문제푼사람수 + 변수이름뭐로하지[infos[1].solved] + 1; // 자기보다 앞 사람 수 + 1 == 등수
		sb.append(me).append(" ");
		if (me <= S) {
			sb.append(0);
		} else {
			// S등 밖이라면, 현재 cut라인 문제수 + 1까지 도달해야한다.
			sb.append(cut_solved + 1 - infos[1].solved);
		}
		sb.append("\n");
	}

	/**
	 * 다른 유저가 풀었을 때 1번 유저 앞에 몇 명 있는지 기록 바꾸는 것
	 */
	public static void checkOther(int userNum) {
		// userNum 유저가 문제를 풀기 전 정보를 가져온다.
		Info userBefore = infos[userNum];
		// 1번 유저의 현재 상태가 어땠는지에 따라 정보를 기록한다.
		if (infos[1].solved <= userBefore.solved) {
			// 만약 1번 user보다 더 많은 문제를 풀게 됐다면 해당 문제에 먼저 도달했음을 기록한다.
			변수이름뭐로하지[userBefore.solved + 1]++; // 해당 문제를 1번 유저보다 먼저 풀었다는 것이다.
			if (infos[1].solved == userBefore.solved) { // 문제 수가 같았다면
				자기보다더많은문제푼사람수++; // 얘가 풀면서 더 많은 문제 푼 사람이 생겼다는 것
				if (userBefore.solIdx < infos[1].solIdx) { // 그런 상황에서 userBefore이 해당문제를 먼저 풀었었다면
					변수이름뭐로하지[userBefore.solved]--; // 해당 문제 먼저 푼 사람 수가 줄었다는 것
				}
			} else {
				// 코딩보다 주석이 더 힘들다.
				변수이름뭐로하지[userBefore.solved]--;
			}
		}
		userSolve(userNum);
	}

	/**
	 * 1번 유저가 풀었을 때 1번 유저 앞 사람 수 바꾸는 것
	 */
	public static void checkFirst() {
		자기보다더많은문제푼사람수 -= 변수이름뭐로하지[infos[1].solved + 1];
		userSolve(1);
	}

	/**
	 * userNum 번 user가 문제를 solve한 경우
	 * 커트라인 조정하는 것
	 */
	public static void userSolve(int userNum) {
		// userNum 유저가 문제를 풀기 전 정보를 가져온다.
		Info userBefore = infos[userNum];
		userBefore.valid = false;
		Info userAfter = new Info(userNum, userBefore.solved + 1);
		solvedList[userAfter.solved].add(userAfter);
		userAfter.solIdx = solvedList[userAfter.solved].size() - 1;
		infos[userNum] = userAfter;
		// userNum 유저가 현재 커트라인 안쪽인지 파악한다.
		if (!checkCut(userBefore)) {
			if (userBefore.solved == cut_solved) {
				// 커트라인 밖에서 안쪽으로 들어온 경우 무조건 앞으로
				pullCutline();
				nextCut();
			}
		}
		else {
			// 커트라인 안에 있던 경우 당기기만
			pullCutline();
		}
	}
	public static void nextCut() {
		if (--cut_idx == -1) {
			cut_solved++;
			cut_idx = solvedList[cut_solved].size() - 1;
		}
		pullCutline();
	}
	public static void pullCutline() {
		while(!solvedList[cut_solved].get(cut_idx).valid) {
			if (--cut_idx == -1) {
				cut_solved++;
				cut_idx = solvedList[cut_solved].size() - 1;
			}
		}
	}

	public static boolean checkCut(Info userInfo) {
		/**
		 * user가 cut보다 더 많은 문제를 풀었거나
		 * cut만큼의 문제를 풀었는데 idx가 cut보다 같거나 작으면
		 * 이미 S등 안쪽의 user정보라는 것이다.
		 */
		return userInfo.solved > cut_solved || userInfo.solved == cut_solved && userInfo.solIdx <= cut_idx;
	}
}