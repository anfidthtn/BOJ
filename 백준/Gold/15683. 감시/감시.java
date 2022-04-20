import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 44,712 KB / 236ms (BOJ 기준)
 */

class Camera{
	int r;
	int c;
	int type;
	public Camera(int r, int c, int type) {
		super();
		this.r = r;
		this.c = c;
		this.type = type;
	}
}
public class Main {
	static int N;
	static int M;
	
	// 시계든 반시계든 큰 상관없고, 붙어있는 방향대로 4방향
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		// 사각지대의 수
		int count = 0;
		for(int i = 0; i < N; i++) {
			String[] slist = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				// 입력을 받으면서
				map[i][j] = slist[j].charAt(0);
				// 사각지대를 카운트한다.
				if (map[i][j] == '0') count++;
			}
		}
		
		// 5의 경우 방향상관없이 감시구역이 나오므로 최초 감시구역을 정해둔다.
		// 최초 감시구역을 정하며 줄어든 사각지대의 수를 빼준다.
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 5번 카메라는 4방향으로 고정되므로, 5번카메라의 감시범위에 대한 초기화를 진행한다.
				// 이거 초기화하면서 다른거도 하려고 switch문 썼는데 그냥 따로 하기로 했고 고치기 귀찮아서 그대로 놔둠
				switch(map[i][j]) {
				case '5':
					// 5번 카메라는 그냥 감시구역화 하면 된다.
					map[i][j] = '#';
					// 4방향에 대해서
					for(int d = 0; d < 4; d++) {
						// 각 방향으로 일자로 지나가며 벽 만나거나 맵 밖으로 나가면 스톱
						// 2번 카메라를 만나면 해당 방향과 수직으로 배치하면 5번카메라와 같은 역할이니 5번 카메라의 역할로 바꾼다.
						// 4번 카메로도 마찬가지로 해당 방향과 수직방향 2방향 모두를 볼 수 있으므로 5번 카메라의 역할로 바꾼다.
						// 이런식으로 하면서 이번 방향에서 나온 0 -> # 개수를 반환받아서 사각지대 수를 줄인다.
						count -= init5(map, i, j, d);
					}
					break;
				}
			}
		}
		List<Camera> cameras = new ArrayList<>();
		// 최초 감시구역 지정 이후 남은 카메라들에 대해 목록화를 한다.
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 남은 카메라 목록화
				switch(map[i][j]) {
				case '1':
				case '2':
				case '3':
				case '4':
					cameras.add(new Camera(i, j, map[i][j]));
				}
			}
		}
		
		// 사각지대 수 - 줄어든 사각지대 수를 출력한다.
		System.out.print(count - fixCamera(map, cameras, 0));
		
	}
	
	// 경계탐색
	public static boolean isOutOfBounds(int row, int col) {
		if (row < 0 || col < 0 || row >= N || col >= M) {
			return true;
		}
		return false;
	}
	
	// 최초의 5번카메라에 대해 초기화 작업을 하는 메소드
	public static int init5(char[][] map, int row, int col, int d) {
		// 0 -> #으로 사각지대가 얼마나 줄어들었는지 세는 count
		int count = 0;
		// 다음 행/열
		int nextRow = row + dr[d];
		int nextCol = col + dc[d];
		// 맵 밖으로 나가면 그만
		if (isOutOfBounds(nextRow, nextCol)) return count;
		// 벽 만나면 그만
		if (map[nextRow][nextCol] == '6') return count;
		// 2, 4는 5의 가지와 만나면 사실상 방향이 고정이다.
		switch(map[nextRow][nextCol]) {
		case '2':
		case '4':
		case '5':
			// 현 위치의 CCTV로 부터 4방향 모두 볼 수 있는 경우이니 고정되니 감시구역으로 바꾸고 온 방향과 수직방향으로 감시구역을 넓힌다.
			// 해당 카메라는 고정이니 카메라에서 감시구역으로 바꾼다.
			map[nextRow][nextCol] = '#';
			// 해당 위치를 고정된 5번으로 간주하고, 현재 진행방향에서 90도 돌린 방향에 대해서도 재귀적 작업을 한다.
			count += init5(map, nextRow, nextCol, (d + 1) % 4);
			// 해당 위치를 고정된 5번으로 간주하고, 현재 진행방향에서 270도 돌린 방향에 대해서도 재귀적 작업을 한다.
			count += init5(map, nextRow, nextCol, (d + 3) % 4);
			break;
		case '0':
			// 사각지대를 감시구역으로 바꾸며 카운트한다.
			count++;
			map[nextRow][nextCol] = '#';
		}
		// 진행하던 방향으로 끝날때까지 진행
		count += init5(map, nextRow, nextCol, d);
		return count;
	}
	
	// 맵을 복사함
	public static char[][] copyMap(char[][] map){
		char[][] newMap = new char[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}
	
	// 한 방향으로 한 줄을 지움
	public static int lineClear(char[][] map, int row, int col, int d) {
		// 0 -> #으로 사각지대가 감시구역으로 바뀌는 수
		int count = 0;
		int nextRow = row + dr[d];
		int nextCol = col + dc[d];
		// 맵 밖으로 나가면 그만
		if (isOutOfBounds(nextRow, nextCol)) return count;
		// 벽 만나면 그만
		if (map[nextRow][nextCol] == '6') return count;
		
		if (map[nextRow][nextCol] == '0') {
			// 바뀌는 것을 카운트
			count++;
			map[nextRow][nextCol] = '#';
		}
		// 해당 방향으로 끝날때까지 진행
		count += lineClear(map, nextRow, nextCol, d);
		return count;
	}
	
	// 감시 구역이 추가된 수(0->#)를 반환한다.
	// 다른말로 하면 사각지대가 줄어든 수를 의미한다.
	public static int fixCamera(char[][] map, List<Camera> cameras, int idx) {
		// 끝까지 도달하면 0이다.
		if (idx == cameras.size()) return 0;
		// 감시구역이 추가된 수
		int count = 0;
		
		Camera nowCamera = cameras.get(idx);
		// 카메라 타입 2는 두 경우, 나머지는 네 경우를 모두 봐야한다.
		for (int d : nowCamera.type == '2' ? new int[] {0, 1} : new int[] {0, 1, 2, 3}) {
			int nowCount = 0;
			char[][] newMap = copyMap(map);
			switch(nowCamera.type) {
			case '4':
				// 4는 3방향을 제거한다.
				nowCount += lineClear(newMap, nowCamera.r, nowCamera.c, (d + 2) % 4);
			case '3':
				// 3은 2방향을 제거한다.
				nowCount += lineClear(newMap, nowCamera.r, nowCamera.c, (d + 1) % 4);
			case '1':
				// 1은 1방향을 제거한다.
				nowCount += lineClear(newMap, nowCamera.r, nowCamera.c, d);
				break;
			case '2':
				// 2는 서로 반대방향을 제거한다.
				nowCount += lineClear(newMap, nowCamera.r, nowCamera.c, d);
				nowCount += lineClear(newMap, nowCamera.r, nowCamera.c, (d + 2) % 4);
			}
			// 다음 카메라에 대해 설정돌린다.
			nowCount += fixCamera(newMap, cameras, idx + 1);
			// 카운트의 최대값을 갱신한다.
			count = Math.max(count, nowCount);
		}
		// 감시구역이 최대 얼마나 추가되는지 반환한다.
		return count;
	}
}