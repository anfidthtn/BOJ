import java.util.Arrays;

class Solution {

	int maxDiff = 0;
	int[] bestShot = null;

	public int[] solution(int n, int[] info) {
		makeCase(info, new int[11], 0, n);
		if (bestShot == null) {
			return new int[] {-1};
		}
		else {
			return bestShot;
		}
	}

	public void makeCase(int[] info, int[] myShot, int idx, int count) {
		// count : 남은 화살 수
		// idx : 현재 쏘려는 과녁

		if (idx == 11) {
			checkDiff(info, myShot, count);
			return;
		}
		// 상대방의 점수 이기기 시도
		if (info[idx] < count) {
			myShot[idx] = info[idx] + 1;
			makeCase(info, myShot, idx + 1, count - info[idx] - 1);
			myShot[idx] = 0;
		}
		// 상대방의 점수 무시하기
		makeCase(info, myShot, idx + 1, count);
		myShot[idx] = 0;
	}

	public void checkDiff(int[] info, int[] myShot, int count) {
		// 남은 화살 있다면 0점에 다 박아버림
		myShot[10] += count;

		int diff = 0;
		for (int i = 0; i <= 10; i++) {
			int score = 10 - i;
			if(info[i] >= myShot[i] && info[i] > 0) {
				diff -= score;
			}
			else if (myShot[i] > 0){
				diff += score;
			}
		}
		if (diff <= 0) {
			return;
		}
		if (diff > maxDiff) {
			maxDiff = diff;
			bestShot = Arrays.copyOf(myShot, 11);
		}
		else if (diff == maxDiff) {
			boolean change = false;
			for(int i = 10; i >= 0; i--) {
				if (bestShot[i] < myShot[i]) {
					change = true;
					break;
				}
				else if (bestShot[i] > myShot[i]) {
					break;
				}
			}
			if (change) {
				bestShot = Arrays.copyOf(myShot, 11);
			}
		}
	}
}