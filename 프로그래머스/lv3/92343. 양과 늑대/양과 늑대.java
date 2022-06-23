import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

class Solution {
//	public static void main(String[] args) {
//		new Solution().solution(new int[] { 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 }, new int[][] { { 0, 1 }, { 1, 2 },
//				{ 1, 4 }, { 0, 8 }, { 8, 7 }, { 9, 10 }, { 9, 11 }, { 4, 3 }, { 6, 5 }, { 4, 6 }, { 8, 9 } });
//		new Solution().solution(new int[] { 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0 }, new int[][] { { 0, 1 }, { 0, 2 },
//				{ 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 4, 8 }, { 6, 9 }, { 9, 10 } });
//	}
	
	class Node{
		int sheep;
		int score;
		boolean status;
	}

	int N;
	int[] scores;
	Set<Integer>[] graph;
	int[] depth;
	int[] parents;
	int[] nodeSize;
	int[] sheeps;
	List<Integer> sheepIdx;
	
	int maxSheep = 0;

	public int solution(int[] info, int[][] edges) {
		int answer = 0;
		N = info.length;
		scores = new int[N];
		graph = new HashSet[N];
		depth = new int[N];
		parents = new int[N];
		nodeSize = new int[N];
		sheeps = new int[N];
		sheepIdx = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph[i] = new HashSet<>();
			parents[i] = i;
			nodeSize[i] = 1;
		}
		for (int[] edge : edges) {
			graph[edge[0]].add(edge[1]);
			graph[edge[1]].add(edge[0]);
		}
		setDepth(0, 1);
		for (int[] edge : edges) {
			if (info[edge[0]] == 0 && info[edge[1]] == 0) {
				merge(edge[0], edge[1]);
			}
		}
		for(int i = 0; i < N; i++) {
			if (i != getParent(i)) {
				continue;
			}
			Set<Integer> newLink = new HashSet<>();
			for(int next : graph[i]) {
				newLink.add(getParent(next));
			}
			graph[i] = newLink;
			scores[i] = -1 * (info[i] * 2 - 1) * nodeSize[i];
			if (info[i] == 0) {
				sheepIdx.add(i);
				sheeps[i] = scores[i];
			}
		}
//		for (int i = 0; i < N; i++) {
//			for(int next : graph[i]) {				
//				System.out.println(i + " " + next);
//			}
//		}
//		for(int i = 0; i < N; i++) {
//			System.out.println(i + " " + scores[i]);
//		}
		
		boolean[] sheepVisited = new boolean[sheepIdx.size()];
		sheepVisited[0] = true;
		boolean[] visited = new boolean[N];
		visited[0] = true;
		outerdfs(0, sheepVisited, visited, scores[0], sheeps[0]);
		answer = maxSheep;

		return answer;
	}
	public void outerdfs(int sheepNow, boolean[] sheepVisited, boolean[] visited, int score, int sheep) {
		if (maxSheep < sheep) {
			maxSheep = sheep;
		}
		for(int i = 0; i < sheepVisited.length; i++) {
			if (sheepVisited[i]) {
				continue;
			}
			sheepVisited[i] = true;
			Node result = new Node();
			result.score = score;
			result.sheep = sheep;
			Stack<Integer> log = new Stack<>();
			boolean[] tracking = new boolean[N];
			tracking[0] = true;
			if (trackingDfs(result, log, visited, tracking, 0, sheepIdx.get(i))) {
				boolean[] newVisited = new boolean[N];
				for(int idx = 0; idx < N; idx++) {
					newVisited[idx] = visited[idx];
				}
				for(int idx : log) {
					newVisited[idx] = true;
				}
				outerdfs(sheepNow, sheepVisited, newVisited, result.score, result.sheep);
			}
			
			sheepVisited[i] = false;
		}
	}
	public boolean trackingDfs(Node result, Stack<Integer> log, boolean[] visited, boolean[] tracking, int now, int target) {
		if (now == target) {
			return true;
		}
		for(int next : graph[now]) {
			if (tracking[next]) {
				continue;
			}
			tracking[next] = true;
			if (!visited[next]) {
				result.score += scores[next];
				result.sheep += sheeps[next];
				if (result.score <= 0) {
					result.score -= scores[next];
					result.sheep -= sheeps[next];
					continue;
				}
			}
			log.add(next);
			if (trackingDfs(result, log, visited, tracking, next, target)) {
				return true;
			}
			log.pop();
			if (!visited[next]) {
				result.score -= scores[next];
				result.sheep -= sheeps[next];
			}
		}
		return false;
	}

	public void merge(int a, int b) {
		if (depth[getParent(a)] > depth[getParent(b)]) {
			int temp = a;
			a = b;
			b = temp;
		}
		Set<Integer> newLink = new HashSet<>();
		for (int next : graph[getParent(a)]) {
			int temp = getParent(next);
			if (temp == getParent(a) || temp == getParent(b)) {
				continue;
			} else {
				newLink.add(next);
			}
		}
		for (int next : graph[getParent(b)]) {
			int temp = getParent(next);
			if (temp == getParent(a) || temp == getParent(b)) {
				continue;
			} else {
				newLink.add(next);
			}
		}
		nodeSize[getParent(a)] += nodeSize[getParent(b)];
		nodeSize[getParent(b)] = 0;
		parents[b] = getParent(a);
		graph[getParent(a)] = newLink;
	}

	public int getParent(int now) {
		if (now == parents[now]) {
			return now;
		}
		return parents[now] = getParent(parents[now]);
	}

	public void setDepth(int now, int nowDepth) {
		if (depth[now] > 0) {
			return;
		}
		depth[now] = nowDepth;
		for (int next : graph[now]) {
			setDepth(next, nowDepth + 1);
		}
	}
}