import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] solution(String[] info, String[] query) {
		List<Integer>[][][][] infoList = new ArrayList[3][2][2][2];
		for (int devLang = 0; devLang < 3; devLang++) {
			for (int dept = 0; dept < 2; dept++) {
				for (int career = 0; career < 2; career++) {
					for (int soulFood = 0; soulFood < 2; soulFood++) {
						infoList[devLang][dept][career][soulFood] = new ArrayList<>();
					}
				}
			}
		}
		for (String infoLine : info) {
			String[] infoWords = infoLine.split(" ");
			int devLang = 0;
			switch (infoWords[0]) {
			case "cpp":
				devLang = 0;
				break;
			case "java":
				devLang = 1;
				break;
			case "python":
				devLang = 2;
				break;
			}
			int dept = infoWords[1].equals("backend") ? 0 : 1;
			int career = infoWords[2].equals("junior") ? 0 : 1;
			int soulFood = infoWords[3].equals("chicken") ? 0 : 1;
			int score = Integer.parseInt(infoWords[4]);
			infoList[devLang][dept][career][soulFood].add(score);
		}
		for (int devLang = 0; devLang < 3; devLang++) {
			for (int dept = 0; dept < 2; dept++) {
				for (int career = 0; career < 2; career++) {
					for (int soulFood = 0; soulFood < 2; soulFood++) {
						infoList[devLang][dept][career][soulFood].sort(Integer::compareTo);
					}
				}
			}
		}
		int[] answer = new int[query.length];
		for (int i = 0; i < query.length; i++) {
			String[] queryWords = query[i].split(" ");
			int[] devLangs = null;
			switch (queryWords[0]) {
			case "cpp":
				devLangs = new int[] { 0 };
				break;
			case "java":
				devLangs = new int[] { 1 };
				break;
			case "python":
				devLangs = new int[] { 2 };
				break;
			case "-":
				devLangs = new int[] { 0, 1, 2 };
				break;
			}
			int[] depts = null;
			switch (queryWords[2]) {
			case "backend":
				depts = new int[] { 0 };
				break;
			case "frontend":
				depts = new int[] { 1 };
				break;
			case "-":
				depts = new int[] { 0, 1 };
				break;
			}
			int[] careers = null;
			switch (queryWords[4]) {
			case "junior":
				careers = new int[] { 0 };
				break;
			case "senior":
				careers = new int[] { 1 };
				break;
			case "-":
				careers = new int[] { 0, 1 };
				break;
			}
			int[] soulFoods = null;
			switch (queryWords[6]) {
			case "chicken":
				soulFoods = new int[] { 0 };
				break;
			case "pizza":
				soulFoods = new int[] { 1 };
				break;
			case "-":
				soulFoods = new int[] { 0, 1 };
				break;
			}
			int score = Integer.parseInt(queryWords[7]);
			for (int devLang : devLangs) {
				for (int dept : depts) {
					for (int career : careers) {
						for (int soulFood : soulFoods) {
							int size = infoList[devLang][dept][career][soulFood].size();
							int idx = search(infoList[devLang][dept][career][soulFood], 0, size - 1, score);
							answer[i] += size - idx - 1;
						}
					}
				}
			}
		}

		return answer;
	}

	public int search(List<Integer> list, int left, int right, int score) {
		if (right < left) {
			return right;
		}
		int mid = (left + right + 1) >> 1;
		if (score <= list.get(mid)) {
			return search(list, left, mid - 1, score);
		}
		else {
			if (left == right) {
				return left;
			}
			return search(list, mid, right, score);
		}
	}
}