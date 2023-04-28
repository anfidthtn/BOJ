import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Student {
		int nation, num, score;

		public Student(int nation, int num, int score) {
			super();
			this.nation = nation;
			this.num = num;
			this.score = score;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Student> students = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			students.add(new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		students.sort((a, b) -> b.score - a.score);
		if (students.get(0).nation == students.get(1).nation) {
			while (students.get(1).nation == students.get(2).nation) {
				students.remove(2);
			}
		}
		for (int i = 0; i < 3; i++) {
			System.out.println(students.get(i).nation + " " + students.get(i).num);
		}
	}
}