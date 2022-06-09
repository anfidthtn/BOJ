import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Member {
		int idx;
		int age;
		String name;

		public Member(int idx, int age, String name) {
			this.idx = idx;
			this.age = age;
			this.name = name;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Member> members = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			members.add(new Member(i, Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		members.sort(new Comparator<Member>() {
			@Override
			public int compare(Member o1, Member o2) {
				if (o1.age - o2.age != 0) {
					return o1.age - o2.age;
				}
				return o1.idx - o2.idx;
			}
		});
		StringBuilder sb = new StringBuilder();
		for (Member member : members) {
			sb.append(member.age).append(" ").append(member.name).append("\n");
		}
		System.out.print(sb.toString());
	}
}