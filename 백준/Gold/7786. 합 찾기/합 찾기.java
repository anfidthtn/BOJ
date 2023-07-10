import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/**
	 * digitSum
	 * [1] : 0 ~ 9에 등장하는 숫자의 합
	 * [2] : 00 ~ 99에 등장하는 숫자의 합
	 * [3] : 000 ~ 999에 등장하는 숫자의 합
	 * ...
	 */
	static List<Long> digitSum = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 인덱스 채우기
		digitSum.add(0l);
		// 0 ~ 9를 합하면 45
		digitSum.add(45l);
		for (int i = 2; i < 16; i++) {
			// 0 ~ 99까지는 0 ~ 9가 각각 10번씩 나오고
			// 10의 자리에서 0 ~ 9가 각각 10번씩 나온다.
			// 0 ~ 999까지는 00 ~ 99가 10번 나오고
			// 100의 자리에서 0 ~ 9가 각각 100번씩 나온다.
			// 0 ~ 9999까지는 000 ~ 999가 각각 10번씩 나오고
			// 1000의 자리에서 0 ~ 9가 각각 1000번씩 나온다.
			long temp = digitSum.get(i - 1) * 10;
			temp += digitSum.get(1) * power(10, i - 1);
			digitSum.add(temp);
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		System.out.print(getSum(b) - getSum(a - 1));
	}

	public static long getSum(long num) {
		if (num <= 0)
			return 0;
		// 10의 expo 제곱을 하면 num과 자리수가 같아지게 함.
		// ex) num이 7123이면 expo = 3, 10 ^ 3 = 1000으로 서로 4자리로 같다.
		int expo = -1;
		// expo를 구하기 위한 임시변수
		long mul = 1;
		while (mul - 1 < num) {
			mul *= 10;
			expo++;
		}
		
		long result = 0;
		// first : 수의 맨 앞 숫자
		// num이 7123이라고 치면 7
		long first = num / power(10, expo);
		// last : 수의 맨 앞 숫자를 제외한 수
		// num이 7123이라고 치면 123
		long last = num % power(10, expo);
		
		/**
		 * num이 7123이라고 가정하면
		 * 맨 앞에 0 ~ 6일 때 뒤에는 000 ~ 999가 1번씩 총 7번 나옴.
		 * 그런 숫자 합을 여기서 더해줌.
		 */
		result += first * digitSum.get(expo);
		
		// 0 ~ 6이 맨 앞자리에 있을 때 1000번씩 나온 것을 더해줌.
		for(int i = 1; i < first; i++) {
			result += i * power(10, expo);
		}
		// 7이 7000 ~ 7123에서 123 + 1회 등장한 것을 더해줌.
		result += first * (last + 1);
		// 123에 대해서 합계를 더해줌
		result += getSum(last);
		return result;
	}

	public static long power(long num, int expo) {
		long result = 1;
		while (expo > 0) {
			if (expo % 2 == 1) {
				result *= num;
			}
			num *= num;
			expo /= 2;
		}
		return result;
	}
}