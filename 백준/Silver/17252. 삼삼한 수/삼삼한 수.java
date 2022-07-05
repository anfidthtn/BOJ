import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	static Set<Long> samsamSet;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long num = Long.parseLong(br.readLine());
		if (num == 0) {
			System.out.println("NO");
			return;
		}
		
		long sam = 1;
		List<Long> samsam = new ArrayList<>();
		while(sam <= Integer.MAX_VALUE) {
			samsam.add(sam);
			sam *= 3;
		}
		
		samsamSet = new TreeSet<>();
		setSamsam(samsamSet, samsam, 0, 0);
		if (samsamSet.contains(num)) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
	}
	public static void setSamsam(Set<Long> samsamSet, List<Long> samsam, long value, int idx) {
		if (value > Integer.MAX_VALUE) {
			return;
		}
		if (idx == samsam.size()) {
			samsamSet.add(value);
			return;
		}
		setSamsam(samsamSet, samsam, value, idx + 1);
		setSamsam(samsamSet, samsam, value + samsam.get(idx), idx + 1);
	}
}