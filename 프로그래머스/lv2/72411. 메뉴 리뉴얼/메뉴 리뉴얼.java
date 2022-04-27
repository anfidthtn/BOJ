import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	public String[] solution(String[] orders, int[] course) {
		Map<Integer, Integer>[] combCountArr = new HashMap[11];
		for (int len : course) {
			combCountArr[len] = new HashMap<>();
		}
		for (String order : orders) {
			for (int len : course) {
				int[] selected = new int[len];
				comb(combCountArr[len], order, selected, 0, 0, len);
			}
		}
		int[] maxCountArr = new int[11];
		for (int len : course) {
			for (int key : combCountArr[len].keySet()) {
				maxCountArr[len] = Math.max(maxCountArr[len], combCountArr[len].get(key));
			}
		}
		List<String> temp = new ArrayList<>();
		for (int len : course) {
			if (maxCountArr[len] <= 1) {
				continue;
			}
			for (int key : combCountArr[len].keySet()) {
				if (combCountArr[len].get(key) == maxCountArr[len]) {
					temp.add(iToS(key));
				}
			}
		}
		temp.sort(String::compareTo);
		String[] answer = new String[temp.size()];
		for(int i = 0; i < temp.size(); i++) {
			answer[i] = temp.get(i);
		}
		return answer;
	}
	public String iToS(int combInt) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 26; i++) {
			if ((combInt & (1 << i)) != 0) {
				sb.append((char)('A' + i));
			}
		}
		return sb.toString();
	}

	public void comb(Map<Integer, Integer> combCount, String order, int[] selected, int idx, int count, int len) {
		if (count == len) {
			int course = 0;
			for (int i = 0; i < len; i++) {
				course += (1 << (order.charAt(selected[i]) - 'A'));
			}
			if (!combCount.containsKey(course)) {
				combCount.put(course, 0);
			}
			combCount.put(course, combCount.get(course) + 1);
			return;
		}
		if (idx == order.length()) {
			return;
		}
		selected[count] = idx;
		comb(combCount, order, selected, idx + 1, count + 1, len);
		comb(combCount, order, selected, idx + 1, count, len);
	}
}