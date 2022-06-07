import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> m = new HashMap<>();
		m.put("January", 1);
		m.put("February", 2);
		m.put("March", 3);
		m.put("April", 4);
		m.put("May", 5);
		m.put("June", 6);
		m.put("July", 7);
		m.put("August", 8);
		m.put("September", 9);
		m.put("October", 10);
		m.put("November", 11);
		m.put("December", 12);
		int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		String[] strs = br.readLine().split(" ");
		int month = m.get(strs[0]);
		int day = Integer.parseInt(strs[1].substring(0, 2));
		int year = Integer.parseInt(strs[2]);
		String[] times = strs[3].split(":");
		int time = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);

		int totaltime = 365;
		if (yearCheck(year)) {
			totaltime++;
		}
		totaltime *= 24 * 60;
		int nowtime = 0;
		for (int mon = 1; mon < month; mon++) {
			nowtime += days[mon];
			if (mon == 2 && yearCheck(year)) {
				nowtime++;
			}
		}
		nowtime += day - 1;
		nowtime *= 24 * 60;
		nowtime += time;
		System.out.println((double) nowtime / totaltime * 100);
	}

	public static boolean yearCheck(int year) {
		if (year % 400 == 0) {
			return true;
		}
		if (year % 4 == 0 && year % 100 != 0) {
			return true;
		}
		return false;
	}
}