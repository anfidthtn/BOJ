import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int numSize = str.length();
		int[] nums = new int[numSize];
		int[] numsCount = new int[10];
		for (int i = 0; i < numSize; i++) {
			nums[i] = str.charAt(i) - '0';
			numsCount[nums[i]]++;
		}
		str = br.readLine();
		int[] targetCount = new int[10];
		for (int i = 0; i < str.length(); i++) {
			targetCount[str.charAt(i) - '0']++;
		}

		int position = 0;
		digitCheck: for (int digit = 9; digit >= 0;) {
			if (numsCount[digit] > 0 && numsCount[digit] == targetCount[digit]) {
				for (int i = position; i < numSize; i++) {
					if (nums[i] == digit) {
						nums[i] = -1;
					}
				}
				numsCount[digit] = 0;
				targetCount[digit] = 0;
				digit--;
				continue;
			}
			if (numsCount[digit] == 0) {
				digit--;
				continue;
			}
			int search = -1;
			for (int i = position; i < numSize; i++) {
				if (nums[i] == digit) {
					search = i;
					break;
				}
			}
			int[] eraseCount = new int[10];
			for (int i = position; i < search; i++) {
				if (nums[i] == -1) {
					continue;
				}
				if (++eraseCount[nums[i]] > targetCount[nums[i]]) {
					digit--;
					continue digitCheck;
				}
			}
			for (int i = position; i < search; i++) {
				if (nums[i] == -1) {
					continue;
				}
				targetCount[nums[i]]--;
				numsCount[nums[i]]--;
				nums[i] = -1;
			}
			position = search + 1;
			numsCount[digit]--;
			digit = 9;
		}
		boolean found = false;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numSize; i++) {
			if (nums[i] != -1) {
				if (nums[i] == 0 && !found) {
					continue;
				}
				else {
					found = true;
					sb.append(nums[i]);
				}
			}
		}
		if (sb.length() == 0) {
			System.out.println(0);
		}
		else {
			System.out.println(sb.toString());
		}
	}
}