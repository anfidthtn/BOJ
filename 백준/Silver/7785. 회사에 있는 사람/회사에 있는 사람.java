import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<String> ans = new ArrayList<>();
		Map<String, Boolean> temp = new TreeMap<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String pppppppppppppppp = st.nextToken();
			if (pppppppppppppppp.equals("enter")) {
				temp.put(name, true);
			} else {
				temp.put(name, false);
			}
		}
		for (String key : temp.keySet()) {
			if (temp.get(key)) {
				ans.add(key);
			}
		}
		ans.sort((a, b) -> b.compareTo(a));
		StringBuilder sb = new StringBuilder();
		for (String s : ans) {
			sb.append(s).append("\n");
		}
		System.out.println(sb.toString());
	}
}