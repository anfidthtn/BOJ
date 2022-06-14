import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Character, Integer> idx = new HashMap<>();
		idx.put('B', 0);
		idx.put('G', 1);
		idx.put('Y', 2);
		idx.put('R', 3);
		String str = br.readLine();
		int[] counts = new int[4];
		for(int i = 0; i < str.length(); i++) {
			if (idx.containsKey(str.charAt(i))) {
				counts[idx.get(str.charAt(i))]++;
			}
		}
		String str2 = br.readLine();
		int[] counts2 = new int[4];
		for(int i = 0; i < str2.length(); i++) {
			if (idx.containsKey(str2.charAt(i))) {
				counts2[idx.get(str2.charAt(i))]++;
			}
		}
		
		int count = 0;
		for(int i = 0; i < 4; i++) {
			while (counts[i] > counts2[i]) {
				for(int j = 0; j < 4; j++) {
					if (i == j) {
						continue;
					}
					while (counts[i] > counts2[i] && counts[j] < counts2[j]) {
						counts[j]++;
						counts[i]--;
						count++;
					}
				}
			}
		}
		System.out.println(count);
	}
}