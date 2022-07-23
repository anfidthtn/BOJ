import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Main {
	static class Duel{
		int value;
		int first;
		int second;
		public Duel(int value, int first, int second) {
			super();
			this.value = value;
			this.first = first;
			this.second = second;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		List<Duel> duels = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				duels.add(new Duel(nums[i] + nums[j], i, j));
			}
		}
		duels.sort(new Comparator<Duel>() {
			@Override
			public int compare(Duel o1, Duel o2) {
				return o1.value - o2.value;
			}
		});
		int nowMin = Integer.MAX_VALUE;
		int left = 0;
		int right = 0;
		while(right < duels.size()) {
			if (left >= right) {
				right++;
				continue;
			}
			int diff = duels.get(right).value - duels.get(left).value;
			int abs = diff >= 0 ? diff : - diff;
			if (nowMin > abs) {
				if (able(duels.get(right), duels.get(left))) {
					nowMin = abs;
				}
			}
			if (diff > 0) {
				left++;
			}
			else {
				right++;
			}
		}
		System.out.println(nowMin);
	}
	public static boolean able(Duel a, Duel b) {
		if (a.first == b.first) {
			return false;
		}
		if (a.first == b.second) {
			return false;
		}
		if (a.second == b.first) {
			return false;
		}
		if (a.second == b.second) {
			return false;
		}
		return true;
	}
}