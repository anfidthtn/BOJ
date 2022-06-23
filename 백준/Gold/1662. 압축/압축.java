import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int idx;
	static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		idx = str.length() - 1;
		System.out.println(getSize());
	}

	public static int getSize() {
		int size = 0;
		while (idx >= 0 && str.charAt(idx) != '(') {
			if (str.charAt(idx) == ')') {
				idx--;
				int tempsize = getSize();
				size += (str.charAt(idx) - '0') * tempsize;
				idx--;
			}
			else {
				size++;
				idx--;
			}
		}
		idx--;
		return size;
	}
}