import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while(!(line = br.readLine()).equals("#")) {
			int count = 0;
			for(int i = 0; i < line.length(); i++) {
				switch(line.charAt(i)) {
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'u':
				case 'A':
				case 'E':
				case 'I':
				case 'O':
				case 'U':
					count++;
					break;
				}
			}
			System.out.println(count);
		}
	}
}