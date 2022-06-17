import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String num = br.readLine();
			if (num.equals("0")) {
				break;
			}
			makeResult(num);
		}
		bw.flush();
		
	}
	public static void makeResult(String num) throws IOException {
		int result = 2 + num.length() - 1;
		for(int i = 0; i < num.length(); i++) {
			switch(num.charAt(i)) {
			case '1':
				result += 2;
				break;
			case '0':
				result += 4;
				break;
			default:
				result += 3;
			}
		}
		bw.write(String.valueOf(result));
		bw.write("\n");
	}
}