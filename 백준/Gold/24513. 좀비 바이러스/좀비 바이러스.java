import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		// R행 C열 배열에  DATA : 입력숫자, TIME : 몇 번째 시간에 방문했는지, POSITION : 이 원소의 위치
		int row, col, data, time;

		public Point(int row, int col, int data) {
			this.row = row;
			this.col = col;
			this.data = data;
			this.time = -1;
		}

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 편의상 R, C로 사용
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		Point[][] map = new Point[R][C];
		// 퍼질 위치를 저장하는 큐다.
		Queue<Point> queue = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = new Point(i, j, Integer.parseInt(st.nextToken()));
				if (map[i][j].data > 0) {
					// 지도에서 초기 1, 2 위치에서 퍼진다. 퍼지는 시각은 0부터 시작
					map[i][j].time = 0;
					queue.add(map[i][j]);
				}
			}
		}
		// 시간 0부터 퍼지기 시작
		int time = 0;
		// 퍼질 곳이 남아있다면 퍼지기 계속 퍼진다.
		while (!queue.isEmpty()) {
			time++;
			// 한 타임에는 이전 큐에 들어있던 원소 수만큼 본다. 마트 계산대에 막대기로 나누는 느낌이라 보면 된다.
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) {
				// 퍼질 위치를 가져온다.
				Point now = queue.poll();
				// 퍼질 위치가 이미 3이라면 퍼트리지 않는다.
				// 3인데도 퍼질 위치에 들어가 있는 이유는 들어간 이후 3이 되기 때문이다. (아래에 추가 설명)
				if (now.data == 3) {
					continue;
				}
				// 4방탐색을 한다.
				for(int d = 0; d < 4; d++) {
					int nextRow = now.row + dr[d];
					int nextCol = now.col + dc[d];
					// 범위 체크
					if (nextRow < 0 || nextCol < 0 || R <= nextRow || C <= nextCol) {
						continue;
					}
					Point next = map[nextRow][nextCol];
					// 벽이거나 이미 3인 곳은 방문하지 않는다.
					if (next.data == -1 || next.data == 3) {
						continue;
					}
					// 방문한 기록이 없거나 "현재 시간에 방문한 곳이면" 방문한다.
					if (next.time == -1 || next.time == time) {
						/*
						 * bit or 연산으로 DATA를 채운다.
						 * 이 의미는 다음과 같다.
						 * now의 데이터가 1일 때
						 * next가 0이면 1이 될 수 있다.
						 * next가 1이면 그대로 1이 된다.
						 * next가 2이면 3이 된다.
						 * 
						 * now의 데이터가 2일 때
						 * next가 0이면 2가 된다.
						 * next가 1이면 3이 된다.
						 * next가 2이면 그대로 2가 된다.
						 * 
						 * 문제는 여기에서 발생한다.
						 * 이미 이 방법으로 queue에 넣은 위치에서 bit or 연산으로 데이터가 1 -> 3으로 바뀌는 등의 상황이 나올 수 있다.
						 * 그래서 위에서 now[data] == 3일 수 있어서 그 경우 continue 시킨 것이다.
						 */
						next.data |= now.data;
						if (next.time == -1) {
							// 미방문한 위치라면 현재 방문 시간을 기록한다.
							next.time = time;
							// 데이터가 3이 아닐 때 다음위치에 넣는 if문을 넣으면 되지만 이미 위에서 3에 대한 처리를 할 이유가 있어 이 if문은 생략한다.
							// 추가로 Time등록시에만 queue에 넣어주면 되기 때문에 time == none인 경우에 넣어준다.
							queue.add(next);
						}
					}
				}
			}
		}
		int[] counts = new int[4];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if (map[i][j].data > 0) {
					counts[map[i][j].data]++;
				}
			}
		}
		for(int i = 1; i <= 3; i++) {
			System.out.print(counts[i] + " ");
		}
	}
}
/*

from collections import deque
import sys

d = ((-1, 0), (0, -1), (1, 0), (0, 1))

def solve():
  # 편의상 R, C로 사용
  R, C = map(int, sys.stdin.readline().split())
  
  # R행 C열 배열에 DATA : 입력숫자, TIME : 몇 번째 시간에 방문했는지, POSITION : 이 원소의 위치
  zido = [[{'DATA': data, 'TIME': None, 'POSITION': (i, j)} for j, data in enumerate(map(int, sys.stdin.readline().split()))] for i in range(R)]

  # 퍼질 위치를 저장하는 큐다.
  queue = deque()
  for i in range(R):
    for j in range(C):
      # 지도에서 초기 1, 2 위치에서 퍼진다. 퍼지는 시각은 0부터 시작
      if zido[i][j]['DATA'] > 0:
        zido[i][j]['TIME'] = 0
        queue.append(zido[i][j])
  
  #시간 0부터 퍼지기 시작
  time = 0
  # 퍼질 곳이 남아있다면 퍼지기 계속 퍼진다.
  while queue:
    # 한 타임씩 퍼진다.
    time += 1
    # 한 타임에는 이전 큐에 들어있던 원소 수만큼 본다. 마트 계산대에 막대기로 나누는 느낌이라 보면 된다.
    q_size = len(queue)
    for _ in range(q_size):
      # 퍼질 위치를 가져온다.
      now = queue.popleft()
      # 퍼질 위치가 이미 3이라면 퍼트리지 않는다.
      # 3인데도 퍼질 위치에 들어가 있는 이유는 들어간 이후 3이 되기 때문이다. (아래에 추가 설명)
      if now['DATA'] == 3:
        continue
      # 4방탐색을 한다.
      for dr, dc in d:
        next_row = now['POSITION'][0] + dr
        next_col = now['POSITION'][1] + dc
        # 범위 체크
        if next_row < 0 or next_col < 0 or R <= next_row or C <= next_col:
          continue
        next = zido[next_row][next_col]
        # 벽이거나 이미 3인 곳은 방문하지 않는다.
        if next['DATA'] == -1 or next['DATA'] == 3:
          continue
        # 방문한 기록이 없거나 "현재 시간에 방문한 곳이면" 방문한다.
        if next['TIME'] == None or next['TIME'] == time:
          '''
          bit or 연산으로 DATA를 채운다.
          이 의미는 다음과 같다.
          now의 데이터가 1일 때
          next가 0이면 1이 될 수 있다.
          next가 1이면 그대로 1이 된다.
          next가 2이면 3이 된다.

          now의 데이터가 2일 때
          next가 0이면 2가 된다.
          next가 1이면 3이 된다.
          next가 2이면 그대로 2가 된다.

          문제는 여기에서 발생한다.
          이미 이 방법으로 queue에 넣은 위치에서 bit or 연산으로 데이터가 1 -> 3으로 바뀌는 등의 상황이 나올 수 있다.
          그래서 위에서 now[data] == 3일 수 있어서 그 경우 continue 시킨 것이다.
          '''
          next['DATA'] |= now['DATA']
          if next['TIME'] == None:
            # 미방문한 위치라면 현재 방문 시간을 기록한다.
            next['TIME'] = time
            # 데이터가 3이 아닐 때 다음위치에 넣는 if문을 넣으면 되지만 이미 위에서 3에 대한 처리를 할 이유가 있어 이 if문은 생략한다.
            # 추가로 Time등록시에만 queue에 넣어주면 되기 때문에 time == none인 경우에 넣어준다.
            queue.append(next)
  counts = [0] * 3
  for i in range(R):
    for j in range(C):
      if zido[i][j]['DATA'] > 0:
        counts[zido[i][j]['DATA'] - 1] += 1
  
  print(*counts)

solve()

*/