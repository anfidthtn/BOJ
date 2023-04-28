import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Student {
		String name;
		int 국어, 영어, 수학;

		public Student(String name, int 국어, int 영어, int 수학) {
			super();
			this.name = name;
			this.국어 = 국어;
			this.영어 = 영어;
			this.수학 = 수학;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Student> list = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list.add(new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		list.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if (o1.국어 == o2.국어) {
					if (o1.영어 == o2.영어) {
						if (o1.수학 == o2.수학) {
							return o1.name.compareTo(o2.name);
						}
						return o2.수학 - o1.수학;
					}
					return o1.영어 - o2.영어;
				}
				return o2.국어 - o1.국어;
			}
		});
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(list.get(i).name).append("\n");
		}
		System.out.print(sb);
	}
}