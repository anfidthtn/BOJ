import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sw = Integer.parseInt(br.readLine());
		boolean[] switchs = new boolean[sw + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= sw; i++) {
			if (st.nextToken().equals("1")) {
				switchs[i] = true;
			}
		}
		int student = Integer.parseInt(br.readLine());
		StringTokenizer st2 = null;
		for(int s = 0; s < student; s++) {
			st2 = new StringTokenizer(br.readLine());
			int stType = Integer.parseInt(st2.nextToken());
			int stSwitch = Integer.parseInt(st2.nextToken());
			switch(stType) {
			case 1:
				for (int i = stSwitch; i <= sw; i += stSwitch) {
					switchs[i] ^= true;
				}
				break;
			case 2:
				switchs[stSwitch] ^= true;
				for (int i = 1; i <= sw; i++) {
					if (stSwitch + i > sw || stSwitch - i <= 0) break;
					if (switchs[stSwitch - i] != switchs[stSwitch + i]) break;
					switchs[stSwitch - i] ^= true;
					switchs[stSwitch + i] ^= true;
				}
				break;
			}
		}
		for (int i = 1; i <= sw; i++) {
			int status = 0;
			if (switchs[i]) status = 1;
			System.out.printf("%d ", status);
			if (i % 20 == 0) System.out.println();
		}
		br.close();
	}
}
