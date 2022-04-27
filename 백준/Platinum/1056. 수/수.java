import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long number = Long.parseLong(br.readLine());
		Map<Long, Long> numBase = new HashMap<>();
		List<Long> numList = new ArrayList<>();
		Map<Long, Long> numMin = new HashMap<>();
		numMin.put(1l, 0l);
		numMin.put(2l, 1l);
		numMin.put(3l, 2l);
		numMin.put(4l, 2l);
		numMin.put(5l, 3l);
		numMin.put(6l, 4l);
		numMin.put(7l, 3l);
		makeList(numBase, numList, number);


		System.out.println(getMin(numMin, numBase, numList, number, Integer.MAX_VALUE));
	}

	public static void makeList(Map<Long, Long> numBase, List<Long> numList, long num) {
		int len = numList.size();
		double numLog = Math.log10(num);
		long minDiff = 1_000_000_000_000_000_000l;
		long i = 2;
		for (; i * i * i <= num; i++) {
			if (numBase.keySet().contains(i)) {
				continue;
			}
			if (i > minDiff) {
				break;
			}
			double log10 = Math.log10(i);
			long value = i * i;
			for (double log = Math.log10(value); log <= numLog - log10; value *= i, log = Math.log10(value)) {
				if (3 * log <= numLog) {
					minDiff = Math.min(i + (Math.abs(num - value) - 1), minDiff);
					name(numBase, numList, value, i);
				}
			}
			minDiff = Math.min(i + (Math.abs(num - value) - 1), minDiff);
			name(numBase, numList, value, i);
			if (numLog + log10 <= 18.00) {
				minDiff = Math.min(i + (Math.abs(num - value * i) - 1), minDiff);
				name(numBase, numList, value * i, i);
			}
		}
		if (numList.size() != len) {
			numList.sort(new Comparator<Long>() {
				@Override
				public int compare(Long o1, Long o2) {
					if (o1 < o2)
						return -1;
					else if (o1 > o2)
						return 1;
					return 0;
				}
			});
		}
	}
	public static void name(Map<Long, Long> numBase, List<Long> numList, long value, long i) {
		if (!numBase.containsKey(value)) {
			numBase.put(value, i);
			numList.add(value);
		}
	}

	public static long getMin(Map<Long, Long> numMin, Map<Long, Long> numBase, List<Long> numList, long num, long minMove) {
		if (numMin.containsKey(num)) {
			return numMin.get(num);
		}
		int firstIdx = getIdx(numList, 0, numList.size() - 1, num);
		for (int i = firstIdx; i >= 0; i--) {
			long nowNum = numList.get(i);
			if (minMove <= num - nowNum) {
				break;
			}
			makeList(numBase, numList, numBase.get(nowNum));
			long nowNumMin = getMin(numMin, numBase, numList, numBase.get(nowNum), minMove) + 1;
			if (nowNumMin + (num - nowNum) < minMove) {
				minMove = nowNumMin + (num - nowNum);
			}
		}
		firstIdx = getIdx(numList, 0, numList.size() - 1, num);
		for (int i = firstIdx + 1; i < numList.size(); i++) {
			long nowNum = numList.get(i);
			if (minMove <= nowNum - num) {
				break;
			}
			makeList(numBase, numList, numBase.get(nowNum));
			long nowNumMin = getMin(numMin, numBase, numList, numBase.get(nowNum), minMove) + 1;
			if (nowNumMin + (nowNum - num) < minMove) {
				minMove = nowNumMin + (nowNum - num);
			}
		}
		long sqrt = (long) Math.sqrt(num);
		if (num <= sqrt * sqrt) {
			sqrt--;
		}
		
		if (minMove > num - sqrt * sqrt) {
			makeList(numBase, numList, sqrt);
			long nowNumMin = getMin(numMin, numBase, numList, sqrt, minMove) + 1;
			if (nowNumMin + (num - sqrt * sqrt) < minMove) {
				minMove = nowNumMin + (num - sqrt * sqrt);
			}
		}
		sqrt++;
		if (minMove > sqrt * sqrt - num) {
			makeList(numBase, numList, sqrt);
			long nowNumMin = getMin(numMin, numBase, numList, sqrt, minMove) + 1;
			if (nowNumMin + (sqrt * sqrt - num) < minMove) {
				minMove = nowNumMin + (sqrt * sqrt - num);
			}
		}
		
		numMin.put(num, minMove);
		return numMin.get(num);
	}

	public static int getIdx(List<Long> numList, int left, int right, long num) {
		if (right <= left) {
			if (numList.get(left) <= num) {
				return left;
			}
			return left - 1;
		}
		int mid = (left + right) >> 1;
		if (num == numList.get(mid)) {
			return mid;
		} else if (num < numList.get(mid)) {
			return getIdx(numList, left, mid - 1, num);
		} else {
			return getIdx(numList, mid + 1, right, num);
		}
	}
}