import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();
		List<Character> list = new ArrayList<>();
		for (int i = 0; i < str.length; i++) {
			if (i == str.length - 1) {
				list.add(str[i]);
			} else {
				if (str[i] == ')' || str[i + 1] == '(') {
					list.add(str[i]);
				} else {
					list.add('l');
					i++;
				}
			}
		}
		long ans = 0;
		int now = 0;
		for (char c : list) {
			switch (c) {
			case '(':
				now++;
				break;
			case ')':
				ans++;
				now--;
				break;
			case 'l':
				ans += now;
				break;
			}
		}
		ans += now;
		System.out.print(ans);
	}
}