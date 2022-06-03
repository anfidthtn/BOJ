import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static class Order{
		int type;
		int x;
		public Order(int type, int x) {
			this.type = type;
			this.x = x;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String firstline;
		while((firstline = br.readLine()) != null) {
			int[] memory = new int[1 << 5];
			for(int i = 0; i < 8; i++) {
				memory[0] <<= 1;
				memory[0] += firstline.charAt(i) - '0';
			}
			for(int idx = 1; idx < 32; idx++) {
				String line = br.readLine();
				for(int i = 0; i < 8; i++) {
					memory[idx] <<= 1;
					memory[idx] += line.charAt(i) - '0';
				}
			}
			int ram = 0;
			int pc = 0;
			while(true) {
				int order = memory[pc];
				int type = (order & (7 << 5)) >> 5;
				int x = order & ((1 << 5) - 1);
				pc = (pc + 1) % (1 << 5);
				if (type == 0) {
					memory[x] = ram;
				}
				else if (type == 1) {
					ram = memory[x];
				}
				else if(type == 2) {
					if (ram == 0) {
						pc = x;
					}
				}
				else if (type == 3) {
					
				}
				else if (type == 4) {
					ram--;
					if (ram < 0) {
						ram = (1 << 8) - 1;
					}
				}
				else if (type == 5) {
					ram++;
					if (ram >= 1 << 8) {
						ram = 0;
					}
				}
				else if (type == 6) {
					pc = x;
				}
				else {
					break;
				}
			}
			System.out.println(String.format("%08d", Integer.parseInt(Integer.toBinaryString(ram))));
		}
	}
}