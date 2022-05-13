import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
	public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
		int[][] minData = new int[k][n + 1];

		List<List<Integer>> graph = new ArrayList<>();
		graph.add(null);
		for (int point = 1; point <= n; point++) {
			for (int log = 0; log < k; log++) {
				minData[log][point] = 1000000;
			}
			graph.add(new LinkedList<>());
		}
		minData[0][gps_log[0]] = 0;
		for (int edge = 0; edge < m; edge++) {
			graph.get(edge_list[edge][0]).add(edge_list[edge][1]);
			graph.get(edge_list[edge][1]).add(edge_list[edge][0]);
		}
		for (int log = 1; log < k; log++) {
			for (int nowPoint = 1; nowPoint <= n; nowPoint++) {
				for (int beforePoint = 1; beforePoint <= n; beforePoint++) {
					if (minData[log - 1][beforePoint] == 1000000) {
						continue;
					}
					if (!graph.get(beforePoint).contains(nowPoint)) {
						continue;
					}
					if (gps_log[log] == nowPoint) {
						minData[log][nowPoint] = Math.min(minData[log][nowPoint], minData[log - 1][beforePoint]);
					}
					else {
						minData[log][nowPoint] = Math.min(minData[log][nowPoint], minData[log - 1][beforePoint] + 1);
					}
				}
			}
		}
		if (minData[k - 1][gps_log[k - 1]] == 1000000) {
			return -1;
		}
		else {
			return minData[k - 1][gps_log[k - 1]]; 
		}
	}
}