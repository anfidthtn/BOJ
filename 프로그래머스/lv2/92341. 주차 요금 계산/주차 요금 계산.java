import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
	public int[] solution(int[] fees, String[] records) {

		Set<Integer> cars = new HashSet<>();
		int[] carsInTime = new int[10000];
		int[] carsTotalTime = new int[10000];
		for (String record : records) {
			String[] info = record.split(" ");
			int time = timeToInt(info[0]);
			int carNum = Integer.parseInt(info[1]);
			if (!cars.contains(carNum)) {
				carsInTime[carNum] = -1;
				cars.add(carNum);
			}
			if (carsInTime[carNum] == -1) {
				carsInTime[carNum] = time;
			} else {
				carsTotalTime[carNum] += time - carsInTime[carNum];
				carsInTime[carNum] = -1;
			}
		}
		int[] answer = new int[cars.size()];
		List<Integer> carsList = new ArrayList<>(cars);
		carsList.sort(Integer::compareTo);
		for (int i = 0; i < carsList.size(); i++) {
			int carNum = carsList.get(i);
			if (carsInTime[carNum] >= 0) {
				carsTotalTime[carNum] += 1439 - carsInTime[carNum];
			}
			answer[i] = getFee(fees, carsTotalTime[carNum]);
		}

		return answer;
	}

	public int getFee(int[] fees, int totalTime) {
		int fee = fees[1];
		totalTime -= fees[0];
		if (totalTime > 0) {
			int unit = (int) Math.ceil((double) totalTime / fees[2]);
			fee += unit * fees[3];
		}
		return fee;
	}

	public int timeToInt(String time) {
		String[] HM = time.split(":");
		return Integer.parseInt(HM[0]) * 60 + Integer.parseInt(HM[1]);
	}
}