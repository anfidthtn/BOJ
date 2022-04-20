import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 행, 열, 3by3 구역별로 저장
		int[] row = new int[9];
		int[] col = new int[9];
		int[] section = new int[9];
		
		// 입력정보 저장
		int[][] map = new int[9][9];
		for(int i = 0; i < 9; i++) {
			String temp = br.readLine();
			for(int j = 0; j < 9; j++) {
				map[i][j] = temp.charAt(j) - '0';
				if (map[i][j] != 0) {
					// 숫자 입력 받으면 해당 행/열/구역에 숫자가 등장했음을 저장
					insertNum(row, i, map[i][j]);
					insertNum(col, j, map[i][j]);
					insertNum(section, getSectionSlotNum(i, j), map[i][j]);
				}
			}
		}
		// dfs 돌림
		dfs(map, 0, 0, row, col, section);
		// 출력
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	public static boolean dfs(int[][] map, int i, int j, int[] row, int[] col, int[] section) {
		// 끝까지 돌았으면 true반환
		if (i == 9)
			return true;
		// 이미 숫자가 있으면 다음 위치 돌리며 그 결과 반환
		if (map[i][j] != 0) {
			return dfs(map, i + (j + 1) / 9, (j + 1) % 9, row, col, section);
		}
		else {
			// 빈 칸은 1 ~ 9까지 탐색
			for (int v = 1; v <= 9; v++) {
				// 행, 열, 구역 중 중복숫자가 나오면 x
				if (!checkValue(row, col, section, i, j, v))
					continue;
				// 숫자를 해당 위치에 넣고 넣었다는 정보 넣어줌
				map[i][j] = v;
				insertNum(row, i, v);
				insertNum(col, j, v);
				insertNum(section, getSectionSlotNum(i, j), v);
				if(dfs(map, i + (j + 1) / 9, (j + 1) % 9, row, col, section))
					// 끝까지 가서 true를 받아온 경우 계속 true 리턴
					return true;
				// 실패해서 true를 못 받았으면 계속 진행
				// 넣었다는 정보를 빼고 숫자를 빼줌
				deleteNum(row, i, v);
				deleteNum(col, j, v);
				deleteNum(section, getSectionSlotNum(i, j), v);
				map[i][j] = 0;
			}
		}
		return false;
	}
	public static int getSectionSlotNum(int i, int j) {
		return (i / 3) * 3 + j / 3;
	}
	public static void insertNum(int[] part, int slot, int value) {
		part[slot] += 1 << value;
	}
	public static void deleteNum(int[] part, int slot, int value) {
		part[slot] -= 1 << value;
	}
	public static boolean checkValue(int[] row, int[] col, int[] section, int i, int j, int v) {
		if ((row[i] & (1 << v)) != 0)
			return false;
		if ((col[j] & (1 << v)) != 0)
			return false;
		if ((section[getSectionSlotNum(i, j)] & (1 << v)) != 0)
			return false;
		return true;
	}
}