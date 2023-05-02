import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] checkX = { 0, 1, 0, 1 }, checkY = { 0, 0, 1, 1 };

	static class Box {
		int lx, ly, lz, px, py;
		long height;

		public Box(int lx, int ly, int lz, int px, int py) {
			this.lx = lx;
			this.ly = ly;
			this.lz = lz;
			this.px = px;
			this.py = py;
			this.height = lz;
		}

		public void check(Box other) {
			if (this.height < other.height + lz) {
				if (!(px + lx <= other.px || py + ly <= other.py || other.px + other.lx <= px
						|| other.py + other.ly <= py)) {
					this.height = other.height + lz;
				}
			}
		}
	}

	static int Lx, Ly, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Lx = Integer.parseInt(st.nextToken());
		Ly = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Box[] boxes = new Box[N];
		long ans = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			boxes[i] = new Box(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			for (int j = i - 1; j >= 0; j--) {
				boxes[i].check(boxes[j]);
			}
			if (ans < boxes[i].height) {
				ans = boxes[i].height;
			}
		}
		System.out.println(ans);
	}
}