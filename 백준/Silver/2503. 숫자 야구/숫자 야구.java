import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Log{
		String num;
		int strike;
		int ball;
		public Log(String num, int strike, int ball) {
			super();
			this.num = num;
			this.strike = strike;
			this.ball = ball;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Log[] logs = new Log[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			logs[i] = new Log(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int count = 0;
		for(int i = 0; i < 1000; i++) {
			char[] num = numtoChararr(i);
			if (num[0] == num[1] || num[1] == num[2] || num[2] == num[0]) {
				continue;
			}
			if (num[0] == '0' || num[1] == '0' || num[2] == '0') {
				continue;
			}
			boolean able = true;
			for(Log log : logs) {
				int strike = 0;
				for(int idx = 0; idx < 3; idx++) {
					if (log.num.charAt(idx) == num[idx]) {
						strike++;
					}
				}
				if (log.strike != strike) {
					able = false;
					break;
				}
				int ball = 0;
				if (log.num.charAt(0) == num[1] || log.num.charAt(0) == num[2]) {
					ball++;
				}
				if (log.num.charAt(1) == num[2] || log.num.charAt(1) == num[0]) {
					ball++;
				}				
				if (log.num.charAt(2) == num[0] || log.num.charAt(2) == num[1]) {
					ball++;
				}
				if (log.ball != ball) {
					able = false;
					break;
				}
			}
			if (able) {
				count++;
			}
		}
		System.out.println(count);
	}
	public static char[] numtoChararr(int num) {
		char[] result = new char[3];
		result[2] = (char) (num % 10 + '0');
		num /= 10;
		result[1] = (char) (num % 10 + '0');
		num /= 10;
		result[0] = (char) (num + '0');
		return result;
	}
}