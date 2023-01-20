import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// N번 roll 한다.
	static int N;
	/**
	 * rolls : N번의 roll의 4개 결과를 bit로 저장
	 * ex) 4개가 7 5 8 1이면
	 * 010000000 (비트 1을 왼쪽으로 7번 밀었다.)
	 * 000100000 (비트 1을 왼쪽으로 5번 밀었다.)
	 * 100000000 (비트 1을 왼쪽으로 8번 밀었다.)
	 * 000000010 (비트 1을 왼쪽으로 1번 밀었다.)
	 * 전부 더해서
	 * 110100010 을 저장한다. (구현 편의상 맨 뒤에 0냅둠)
	 */
	static int[] rolls;
	/**
	 * 가상의 주사위를 만들 때 쓰는 리스트
	 * 주사위를 상단 4개면, 하단 4개 면으로 생각할 수 있고
	 * 그것을 0 ~ 3, 4 ~ 7 인덱스로 생각할 수 있음.
	 */
	static int[] selectList;
	/**
	 * 가상의 주사위 객체
	 */
	static Dice dice;

	/**
	 * 가상의 주사위를 채우고나서 정답을 미리 적어둘 때 쓰는 용도
	 * 예를 들어 인덱스 0이 맨 위로 올 때는 인덱스 3이 10시방향, 인덱스 1이 2시방향, 인덱스 4가 6시 방향에 보인다 라고 생각하면
	 * 됨.
	 */
	static int[][] diceAdj = { //
			{ 3, 0, 1, 4 }, //
			{ 0, 1, 2, 5 }, //
			{ 1, 2, 3, 6 }, //
			{ 2, 3, 0, 7 }, //
			{ 7, 4, 5, 0 }, //
			{ 4, 5, 6, 1 }, //
			{ 5, 6, 7, 2 }, //
			{ 6, 7, 4, 3 } //
	};

	/**
	 * 주사위 클래스
	 */
	static class Dice {
		int[] faceAnswer;

		public Dice() {
			super();
			/**
			 * 주사위 클래스 생성 시 int배열 한 번만 만들어서 메모리 낭비 최대한 줄이기
			 */
			this.faceAnswer = new int[8];
		}

		public void fillDice() {
			/**
			 * 정답은 0으로 일단 채움
			 */
			for (int i = 0; i < 8; i++) {
				faceAnswer[i] = 0;
			}
			/**
			 * 위에서 정의한 인덱스를 기반으로 해당 주사위에서 정답으로 나올 수 있는 8개의 경우의 수를 채움.
			 */
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 4; j++) {
					faceAnswer[i] += 1 << selectList[diceAdj[i][j]];
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		rolls = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				/**
				 * 주사위 굴린 결과 4개의 순서는 상관없으니 정보를 한 번에 묶을 수 있는 비트화를 하여 사용
				 */
				rolls[i] += 1 << Integer.parseInt(st.nextToken());
			}
		}
		selectList = new int[8];
		dice = new Dice();
		/**
		 * 40320개의 조합을 다 해보고 가능한 경우가 있으면 의심을 못하고
		 * 가능한 경우가 하나도 없으면 검!거!
		 */
		if (comb(0, 0)) {
			System.out.print("Hmm...");
		} else {
			System.out.print("You're gonna die!");
		}
	}

	public static boolean comb(int idx, int selected) {
		/**
		 * 8개를 다 골랐으면 고른 주사위로 돌려봄.
		 */
		if (idx == 8) {
			return combCheck();
		}
		/**
		 * 8개를 다 못 골랐으면 골라봄
		 */
		for (int i = 1; i <= 8; i++) {
			/**
			 * 이미 고른 숫자는 고르지 않음
			 */
			if ((selected & (1 << i)) != 0) {
				continue;
			}
			/**
			 * 고르고 돌림
			 */
			selectList[idx] = i;
			if (comb(idx + 1, selected + (1 << i))) {
				return true;
			}
		}
		return false;
	}

	public static boolean combCheck() {
		/**
		 * 고른 결과로 가상의 주사위 면을 채우고
		 * 채운 면에 해당하는 정답을 만듦
		 */
		dice.fillDice();
		/**
		 * N개 굴린 결과별로 이 주사위에서 나오는 roll결과인지 확인
		 */
		caseCheck: for (int i = 0; i < N; i++) {
			for (int j = 0; j < 8; j++) {
				/**
				 * 8면 중 1개라도 일치를 하면 일단 이 roll결과는 이 주사위에서 믿을 수 있음.
				 */
				if (rolls[i] == dice.faceAnswer[j])
					continue caseCheck;
			}
			/**
			 * 8면 중 0개 일치하는 roll결과가 있다면 이 주사위는 아니라는 소리
			 */
			return false;
		}
		/**
		 * N개 굴린 결과 모두 이 주사위에서 믿을 수 있는 결과로 판정되면 이 주사위였다. 라고 말하면 되니 true 반환.
		 */
		return true;
	}
}