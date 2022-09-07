import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			if (name.equals("#")) {
				break;
			}
			sb.append(name).append(" ");
			if (Integer.parseInt(st.nextToken()) > 17) {
				sb.append("Senior\n");
			}
			else if (Integer.parseInt(st.nextToken()) >= 80) {
				sb.append("Senior\n");
			}
			else {
				sb.append("Junior\n");
			}
		}
		System.out.print(sb.toString());
	}
}