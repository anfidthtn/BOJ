import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 아이디어의 출발은
 * 1. '한 면의 합을 최대로'
 * 2. '위 아래는 다른 주사위와 같은 수인 상태로 옆면 돌리는건 자유'
 * 3. '한 주사위에 중복되는 숫자 없음'
 * 에서 시작한다.
 * 2.의 조건과 3.의 조건으로 인해
 * 첫 주사위를 놓는 6가지 방법에 따라 모든 주사위에서 사용가능한 옆면 4가지가 정해진다.
 * 이때 1.의 조건으로 각 주사위별로 사용가능한 4가지 수 중 가장 높은 수의 합을 구하면 된다.
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 주사위 면 정보를 받을 곳
		int[][] diceInfo = new int[n][6];
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 6; j++) {
				diceInfo[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxValue = 0;
		for (int i = 1; i <= 6; i++) {
			// 첫 주사위의 아랫면이 1인 경우부터 6인 경우까지
			
			// 첫 주사위의 바닥면 설정
			int nextExceptValue = i;
			ArrayList<Integer> exceptValues;
			int tempMax = 0;
			for (int j = 0; j < n; j++) {
				// 다음 주사위 세팅
				exceptValues = new ArrayList<>();
				// 바닥면 넣고
				exceptValues.add(nextExceptValue);
				// 반대면 구하고
				nextExceptValue = getExceptValueValue(diceInfo[j], nextExceptValue);
				// 반대면 넣고
				exceptValues.add(nextExceptValue);
				// 옆면 최대값 구해주고
				tempMax += getMaxValue(diceInfo[j], exceptValues);
			}
			// 최대값 비교 해주고
			maxValue = Math.max(tempMax, maxValue);
		}
		System.out.print(maxValue);
	}
	
	// value와 반대면에 있는 숫자를 return해준다.
	public static int getExceptValueValue(int[] dice, int value){
		for (int i = 0; i < 6; i++) {
			if (dice[i] == value) {
				switch (i) {
				case 0:
					// A는 F
					return dice[5];
				case 1:
					// B는 D
					return dice[3];
				case 2:
					// C는 E
					return dice[4];
				case 3:
					return dice[1];
				case 4:
					return dice[2];
				case 5:
					return dice[0];
				}
			}
		}
		return -1;
	}
	
	// 제외할 값을 제외하고 최대값을 구해준다.
	public static int getMaxValue(int[] dice, ArrayList<Integer> exceptValues) {
		int maxNum = 0;
		for (int i = 0; i < 6; i++) {
			// 제외 숫자면 검사 x
			if (exceptValues.contains(dice[i])) continue;
			maxNum = Math.max(maxNum, dice[i]);
		}
		return maxNum;
	}
}