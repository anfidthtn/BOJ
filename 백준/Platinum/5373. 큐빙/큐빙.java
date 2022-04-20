import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Block{
	char U = ' ';
	char D = ' ';
	char F = ' ';
	char B = ' ';
	char L = ' ';
	char R = ' ';

	// 위에서 바라보고 시계/반시계로 돌림
	public void U(boolean clock) {
		if (clock) {
			char temp = F;
			F = R;
			R = B;
			B = L;
			L = temp;
		}
		else {
			char temp = F;
			F = L;
			L = B;
			B = R;
			R = temp;
		}
	}
	// 왼쪽에서 바라보고 시계/반시계로 돌림
	public void L(boolean clock) {
		if (clock) {
			char temp = U;
			U = B;
			B = D;
			D = F;
			F = temp;
		}
		else {
			char temp = U;
			U = F;
			F = D;
			D = B;
			B = temp;
		}
	}
	// 정면에서 바라보고 시계/반시계로 돌림
	public void F(boolean clock) {
		if (clock) {
			char temp = U;
			U = L;
			L = D;
			D = R;
			R = temp;
		}
		else {
			char temp = U;
			U = R;
			R = D;
			D = L;
			L = temp;
		}
	}
}

class Cube{
	Block[][][] cube;
	public Cube() {
		// 정면에서 봤을 때 왼쪽이 x = 0, 오른쪽이 x = 2
		// 뒤가 y = 0, 앞이 y = 2 
		// 밑이 z = 0, 위가 z = 2
		cube = new Block[3][3][3];
		for (int x = 0; x < 3; x++) {
			cube[x] = new Block[3][3];
			for (int y = 0; y < 3; y++) {
				cube[x][y] = new Block[3];
				for (int z = 0; z < 3; z++) {
					cube[x][y][z] = new Block();
					// 해당 블록의 위치에 따라 해당 블록 면의 초기색 저장
					if (x == 0) cube[x][y][z].L = 'g';
					if (x == 2) cube[x][y][z].R = 'b';
					if (y == 0) cube[x][y][z].B = 'o';
					if (y == 2) cube[x][y][z].F = 'r';
					if (z == 0) cube[x][y][z].D = 'y';
					if (z == 2) cube[x][y][z].U = 'w';
				}
			}
		}
	}
	
	public void Rotate(String order) {
		char side = order.charAt(0);
		char dir = order.charAt(1);
		if (side == 'U' || side == 'D') {
			// U+와 D-는 위에서 보면 둘 다 시계방향으로 돌아간다
			// U-와 D+는 위에서 보면 둘 다 반시계방향으로 돌아간다.
			
			// 기본 U에서 볼 때 방향대로
			boolean clock = dir == '+' ? true : false;
			// U냐 D냐에 따라서 Z축(높이)결정
			int z = 2;
			if (side == 'D') {
				z = 0;
				// D에서 볼때는 U에서 볼 때와 반대니까 반대로 바꿔줌
				clock ^= true;
			} 
			
			for(int x = 0; x < 3; x++) {
				for(int y = 0; y < 3; y++) {
					// 맨 위에 블록들을 전부 위에서 봤을 때 돌려야 할 방향으로 돌린 색의 상태로 만든다.
					cube[x][y][z].U(clock);
				}
			}
			// 일단 빡구현 해놓고 나중에 수정할 예정
			if (clock) {
				// 꼭지점 부분을 시계방향으로
				Block temp = cube[0][0][z];
				cube[0][0][z] = cube[0][2][z];
				cube[0][2][z] = cube[2][2][z];
				cube[2][2][z] = cube[2][0][z];
				cube[2][0][z] = temp;
				// 변 부분을 시계방향으로
				temp = cube[1][0][z];
				cube[1][0][z] = cube[0][1][z];
				cube[0][1][z] = cube[1][2][z];
				cube[1][2][z] = cube[2][1][z];
				cube[2][1][z] = temp;
			}
			else {
				// 꼭지점 부분을 반시계방향으로
				Block temp = cube[0][0][z];
				cube[0][0][z] = cube[2][0][z];
				cube[2][0][z] = cube[2][2][z];
				cube[2][2][z] = cube[0][2][z];
				cube[0][2][z] = temp;
				// 변 부분을 반시계방향으로
				temp = cube[1][0][z];
				cube[1][0][z] = cube[2][1][z];
				cube[2][1][z] = cube[1][2][z];
				cube[1][2][z] = cube[0][1][z];
				cube[0][1][z] = temp;
			}
		}
		if (side == 'L' || side == 'R') {
			// L+와 R-는 L에서 보면 둘 다 시계방향으로 돌아간다
			// L-와 R+는 L에서 보면 둘 다 반시계방향으로 돌아간다.
			
			// 기본 L에서 볼 때 방향대로
			boolean clock = dir == '+' ? true : false;
			// L냐 R냐에 따라서 X축(좌우)결정
			int x = 0;
			if (side == 'R') {
				x = 2;
				// R에서 볼때는 L에서 볼 때와 반대니까 반대로 바꿔줌
				clock ^= true;
			} 
			
			for(int y = 0; y < 3; y++) {
				for(int z = 0; z < 3; z++) {
					// 맨 위에 블록들을 전부 위에서 봤을 때 돌려야 할 방향으로 돌린 색의 상태로 만든다.
					cube[x][y][z].L(clock);
				}
			}
			// 일단 빡구현 해놓고 나중에 수정할 예정
			if (clock) {
				// 꼭지점 부분을 시계방향으로
				Block temp = cube[x][0][0];
				cube[x][0][0] = cube[x][2][0];
				cube[x][2][0] = cube[x][2][2];
				cube[x][2][2] = cube[x][0][2];
				cube[x][0][2] = temp;
				// 변 부분을 시계방향으로
				temp = cube[x][1][0];
				cube[x][1][0] = cube[x][2][1];
				cube[x][2][1] = cube[x][1][2];
				cube[x][1][2] = cube[x][0][1];
				cube[x][0][1] = temp;
			}
			else {
				// 꼭지점 부분을 반시계방향으로
				Block temp = cube[x][0][0];
				cube[x][0][0] = cube[x][0][2];
				cube[x][0][2] = cube[x][2][2];
				cube[x][2][2] = cube[x][2][0];
				cube[x][2][0] = temp;
				// 변 부분을 반시계방향으로
				temp = cube[x][1][0];
				cube[x][1][0] = cube[x][0][1];
				cube[x][0][1] = cube[x][1][2];
				cube[x][1][2] = cube[x][2][1];
				cube[x][2][1] = temp;
			}
		}
		if (side == 'F' || side == 'B') {
			// F+와 B-는 F에서 보면 둘 다 시계방향으로 돌아간다
			// F-와 B+는 F에서 보면 둘 다 반시계방향으로 돌아간다.
			
			// 기본 F에서 볼 때 방향대로
			boolean clock = dir == '+' ? true : false;
			// F냐 B냐에 따라서 Y축(앞뒤)결정
			int y = 2;
			if (side == 'B') {
				y = 0;
				// B에서 볼때는 F에서 볼 때와 반대니까 반대로 바꿔줌
				clock ^= true;
			} 
			
			for(int x = 0; x < 3; x++) {
				for(int z = 0; z < 3; z++) {
					// 맨 위에 블록들을 전부 위에서 봤을 때 돌려야 할 방향으로 돌린 색의 상태로 만든다.
					cube[x][y][z].F(clock);
				}
			}
			// 일단 빡구현 해놓고 나중에 수정할 예정
			if (clock) {
				// 꼭지점 부분을 시계방향으로
				Block temp = cube[0][y][0];
				cube[0][y][0] = cube[2][y][0];
				cube[2][y][0] = cube[2][y][2];
				cube[2][y][2] = cube[0][y][2];
				cube[0][y][2] = temp;
				// 변 부분을 시계방향으로
				temp = cube[1][y][0];
				cube[1][y][0] = cube[2][y][1];
				cube[2][y][1] = cube[1][y][2];
				cube[1][y][2] = cube[0][y][1];
				cube[0][y][1] = temp;
			}
			else {
				// 꼭지점 부분을 반시계방향으로
				Block temp = cube[0][y][0];
				cube[0][y][0] = cube[0][y][2];
				cube[0][y][2] = cube[2][y][2];
				cube[2][y][2] = cube[2][y][0];
				cube[2][y][0] = temp;
				// 변 부분을 반시계방향으로
				temp = cube[1][y][0];
				cube[1][y][0] = cube[0][y][1];
				cube[0][y][1] = cube[1][y][2];
				cube[1][y][2] = cube[2][y][1];
				cube[2][y][1] = temp;
			}
		}
	}
	
	public void printU(StringBuilder sb) {
		/**
		 *  B
		 * LUR
		 *  F
		 * 상태로 두고 U를 봤을 때
		 * 세로(행)는 y축, 가로(열)은 x축이다.
		 * 그래서 y -> x 순 2중포문 출력이다.
		 */
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				sb.append(cube[x][y][2].U);
			}
			sb.append("\n");
		}
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			Cube cube = new Cube();
			while (st.hasMoreTokens()) cube.Rotate(st.nextToken());
			cube.printU(sb);
		}
		System.out.print(sb.toString());
	}
}