import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 높이와 너비 입력받기
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			// 패딩까지 해서 맵 입력 받기
			// .으로 패딩 하나 더 해서 바깥부분을 더 수월하게 검색할 수 있도록 지정
			char[][] map = new char[h + 4][w + 4];
			
			for (int i = 2; i <= h + 1; i++) {
				String line = br.readLine();
				for (int j = 2; j <= w + 1; j++) {
					map[i][j] = line.charAt(j - 2);
				}
			}
			for (int i = 1; i <= h + 2; i++) {
				map[i][1] = '.';
				map[i][w + 2] = '.';
			}
			for (int j = 1; j <= w + 2; j++) {
				map[1][j] = '.';
				map[h + 2][j] = '.';
			}
			
			// 방문여부 등록
			boolean[][] isVisited = new boolean[h + 4][w + 4];
			// 알파벳들에 대해 key를 갖고 있는지
			boolean[] key = new boolean[26];
			// key를 갖고있지 않은 것들에 대해 방문하다 마주친 문들이 어디있었는지
			Map<Character, List<Point>> pleaseKey = new HashMap<>();
						
			// 갖고있는 키 등록
			String keys = br.readLine().trim();
			if (!keys.equals("0")) {
				for (int i = 0; i < keys.length(); i++) {
					key[keys.charAt(i) - 'a'] = true;
				}
			}
			
			// 탐색대기 지점
			Queue<Point> waiting = new LinkedList<>();
			// 패딩으로 인해 바깥쪽 빈칸임이 보장된 곳부터 탐색 시작
			waiting.add(new Point(1, 1));
			isVisited[1][1] = true;
			
			int docCount = 0;
			
			while(!waiting.isEmpty()) {
				// 탐색지점 꺼내오고
				Point now = waiting.poll();
				// 4방탐색
				for (int d = 0; d < 4; d++) {
					int nextRow = now.row + dr[d];
					int nextCol = now.col + dc[d];
					if (isVisited[nextRow][nextCol]) continue;
					isVisited[nextRow][nextCol] = true;
					char next = map[nextRow][nextCol];
					// 열쇠 발견하면
					if ('a' <= next && next <= 'z') {
						// 키 찾았다고 하기
						key[next - 'a'] = true;
						// 만약 키가 필요한 문을 발견했었다면 방문지점에 넣어주기
						if (pleaseKey.containsKey(next)) {
							// 방문 지점에 모두 넣어주고
							waiting.addAll(pleaseKey.get(next));
							// 키 필요하다는 정보를 없애줌
							pleaseKey.remove(next);
						}
						waiting.add(new Point(nextRow, nextCol));
					}
					// 문을 발견하면
					else if('A' <= next && next <= 'Z') {
						// 열쇠가 있는지 체크한다.
						if (key[next - 'A']) {
							// 열쇠가 있다면 다음 방문지점으로 등록하면 된다.
							waiting.add(new Point(nextRow, nextCol));
						}
						else {
							// 열쇠가 없다면 키가 필요하다는 정보를 등록한다.
							Character nextKey = (char) ((char) next - 'A' + 'a');
							// 새로 나온 알파벳이면 해시맵의 키부터 등록
							if (!pleaseKey.containsKey(nextKey)) pleaseKey.put(nextKey, new LinkedList<>());
							// 해시맵에서 키로 찾아서 등록
							pleaseKey.get(nextKey).add(new Point(nextRow, nextCol));
						}
					}
					else if (next == '$') {
						docCount++;
						waiting.add(new Point(nextRow, nextCol));
					}
					else if (next == '.') {
						waiting.add(new Point(nextRow, nextCol));						
					}
				}
			}
			sb.append(docCount).append('\n');
		}
		System.out.print(sb.toString());
	}
}