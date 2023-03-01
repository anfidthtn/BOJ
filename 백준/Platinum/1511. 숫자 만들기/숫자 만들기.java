import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] cards;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cards = new int[10];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int temp = 0;
		for (int i = 0; i < 10; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
			temp += i * cards[i];
		}
		// 0밖에 없으면 0이다.
		if (temp == 0) {
			System.out.print(0);
			return;
		}
		// 현재 card 보유 상태에 따른 최대 길이를 구한다.
		int len = getLen(0);
		int before = 0;
		StringBuilder sb = new StringBuilder();
		// 최대 길이만큼 채워나갈거다.
		for (int idx = 0; idx < len; idx++) {
			// 후보군을 9부터 0까지 본다.
			for (int digit = 9; digit >= 0; digit--) {
				// 바로 전에 사용한 수거나, 0개 남은 카드라면 넘어간다.
				if (digit == before || cards[digit] == 0) {
					continue;
				}
				// 사용가능한 카드면 사용을 한 것으로 가정하고 체킹해본다.
				cards[digit]--;
				// 10자리 남았을 때 digit이라는 수를 쓰고 뒤에 9자리를 채울 수 있어야한다.
				// 9자리를 채울 수 없다면 digit이라는 수를 쓸 수 없다.
				// 9자리를 채울 수 있다면 digit이라는 수를 쓰면 된다.
				if (getLen(digit) == len - 1 - idx) {
					// 채울 수 있을 때 digit을 쓴다.
					sb.append(digit);
					// digit이 다음 자리의 before이 된다.
					before = digit;
					break;
				}
				// 사용불가능하다면 card를 복구한다.
				cards[digit]++;
			}
		}
		System.out.print(sb.toString());
	}

	public static int getLen(int before) {
		int max = -1;
		int maxCount = 0;
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			if (max < cards[i]) {
				max = cards[i];
				maxCount = 1;
			} else if (max == cards[i]) {
				maxCount++;
			}
			sum += cards[i];
		}
		if (sum >= 2 * max) {
			// 균등배분이면 적절하게 배치해서 sum만큼의 배치 가능
			return sum;
		} else if (sum == 2 * max - 1 && cards[before] == max && maxCount == 1) {
			// 카드 1 3개 카드 2 2개 남았는데 바로 전이 1이라면 2121 이렇게 4개가 최대다.
			// 바로 전이 1이 아니라면 12121 이렇게 5개 다 쓸 수 있다.
			return sum - 1;
		}
		// 1번 5개
		// 2번 1개
		// 3번 1개
		// 이런식이라 쳤을 때 이전 수가 1이면 3121, 1이 아니면 13121 이렇게 채워질 수 있다.
		return (sum - max) * 2 + (cards[before] != max || maxCount != 1 ? 1 : 0);
	}
}