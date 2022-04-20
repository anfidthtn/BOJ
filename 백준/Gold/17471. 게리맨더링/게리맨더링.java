import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] population;
	static List<Set<Integer>> adj;
	static int totalSum;
	static int minDiff;
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N 입력 받기
		N = Integer.parseInt(br.readLine());
		// 인구수 저장받을 배열 만들기
		population = new int[N + 1];
		// 연결관계 집합 입력받을 배열 만들기
		adj = new ArrayList<>();
		// 인덱스 1부터 쓰려고 인덱스 0을 채워버리기
		adj.add(null);
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 전체 인구 합계 미리 구해두기
		totalSum = 0;
		// 입력받기
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			totalSum += population[i];
			adj.add(new HashSet<>());
		}
		// 연결관계 입력 받기
		for(int s = 1; s <= N; s++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while(st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				adj.get(s).add(num);
				adj.get(num).add(s);
			}
		}
		
		// 현재까지 차이 최저 저장
		minDiff = Integer.MAX_VALUE;
		// nC1 ~ nC(n/2) 까지 돌림
		for (int group = 1; group <= N / 2; group++) {
			boolean[] selected = new boolean[N + 1];
			// 인덱스 1번부터, group개를 한 그룹으로, 현재까지 선택된 인구수 합계 0
			comb(selected, 1, group, 0);
		}
		if (minDiff == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(minDiff);
		}
	}
	
	public static void comb(boolean[] selected, int idx, int remain, int selectSum) {
		// 인덱스 오버 끝
		if(idx > N + 1)
			return;
		// 조합 다 만들었으면 돌림
		if (remain == 0) {
			// 현재 조합 차이가 최저보다 같거나 높으면 끝
			if (Math.abs(totalSum - selectSum * 2) >= minDiff)
				return;
			// 그룹화가 될 수 있어야 현재 차이 저장
			if (getIsAvailable(selected)) {
				minDiff = Math.abs(totalSum - selectSum * 2);
			}
			return;
		}
		if (idx <= N) {
			// 이번 꺼 안 넣고 돌리기
			comb(selected, idx + 1, remain, selectSum);
			// 이번 꺼 넣고 돌리기
			selected[idx] = true;
			comb(selected, idx + 1, remain - 1, selectSum + population[idx]);
			selected[idx] = false;
		}
	}
	public static boolean getIsAvailable(boolean[] selected) {
		// 두 그룹으로 나눠서, 선택된 녀석은 0팀 안 된 녀석은 1팀 이런식으로
		boolean[][] visit = new boolean[2][N + 1];
		int[] start = new int[2];
		for (int i = 1; i <= N; i++) {
			// 선택된 녀석은 0팀으로, 0팀의 원소는 1팀이 탐색하면 안 돼서 true
			if (selected[i]) {
				visit[1][i] = true;
				start[0] = i;
			}
			// 선택 안 된 녀석은 1팀으로, 1팀의 원소는 0팀이 탐색하면 안 돼서 true
			else {
				visit[0][i] = true;
				start[1] = i;
			}
		}
		for(int t = 0; t <= 1; t++) {
			visit[t][start[t]] = true;
			// dfs로 visit 배열을 그냥 다 칠해버림
			dfs(visit[t], start[t]);
			// 안 칠해진 곳이 있으면 false
			for (int i = 1; i <= N; i++) {
				if(!visit[t][i])
					return false;
			}
		}
		// 두 팀 다 칠해지면 true
		return true;
	}
	public static void dfs(boolean[] visit, int node) {
		// 단순 방문
		for(int next : adj.get(node)) {
			if (visit[next])
				continue;
			visit[next] = true;
			dfs(visit, next);
		}
	}
}