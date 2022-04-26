import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static class Dictionary{
		boolean exist;
		Dictionary[] next = new Dictionary[10];
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int N = Integer.parseInt(br.readLine());
			boolean able = true;
			Dictionary dictionary = new Dictionary();
			String[] numbers = new String[N];
			for(int i = 0; i < N; i++) {
				numbers[i] = br.readLine();
			}
			Arrays.sort(numbers);
			for(String number : numbers) {
				if (able) {
					able = checkNumber(dictionary, number, 0);
				}
				else {
					break;
				}
			}
			if (able) {
				sb.append("YES\n");
			}
			else {
				sb.append("NO\n");
			}
		}
		System.out.print(sb.toString());
	}
	public static boolean checkNumber(Dictionary now, String phoneNumber, int idx) {
		if (now.exist) {
			return false;
		}
		if (phoneNumber.length() == idx) {
			now.exist = true;
			return true;
		}
		int number = phoneNumber.charAt(idx) - '0';
		if (now.next[number] == null) {
			now.next[number] = new Dictionary();
		}
		return checkNumber(now.next[number], phoneNumber, idx + 1);
	}
}