import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int maxLen = 0;
		ArrayList<Integer> result = null;
		for (int i = n / 2; i <= n; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(n);
			getLen(n, i, temp);
			int nowlen = temp.size();
			if (maxLen < nowlen) {
				maxLen = nowlen;
				result = temp;
			}
		}
		System.out.println(maxLen);
		for (int i : result) {
			System.out.print(i + " ");
		}
	}
	public static void getLen(int n, int start, ArrayList<Integer> temp) {
		if (start < 0) {
			return;
		}
		temp.add(start);
		getLen(start, n - start, temp);
	}
}