import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int ysCount = 0;
		int msCount = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int time = Integer.parseInt(st.nextToken());
			ysCount += YS(time);
			msCount += MS(time);
		}
		if (ysCount < msCount) {
			System.out.println("Y " + ysCount);
		}
		else if (ysCount > msCount){
			System.out.println("M " + msCount);			
		}
		else {
			System.out.println("Y M " + msCount);			
		}
	}
	public static int YS(int time) {
		return time / 30 * 10 + 10;
	}
	public static int MS(int time) {
		return time / 60 * 15 + 15;
	}
}