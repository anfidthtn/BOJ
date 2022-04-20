import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = t2I(br.readLine());
		int s = t2I(br.readLine());
		if (n > s) {
			n -= 86400;
		}
		System.out.println(i2T(s - n));
	}
	
	public static int t2I(String time) {
		String[] frag = time.split(":");
		return 3600 * Integer.parseInt(frag[0]) + 60 * Integer.parseInt(frag[1]) + Integer.parseInt(frag[2]);
	}
	
	public static String i2T(int time) {
		return String.format("%02d:%02d:%02d", time / 3600, (time % 3600) / 60, time % 60);
	}
}