import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class PRO_모의고사 {

	public static void main(String[] args) {
		int[] answers = { 1, 2, 3, 4, 5 };
		System.out.println(Arrays.toString(solution(answers)));
	}
	
	static class Dot{
		int no, cnt;

		public Dot(int no, int cnt) {
			this.no = no;
			this.cnt = cnt;
		}
	}
	public static int[] solution(int[] answers) {
		// 문제수
		int len = answers.length;

		// 학생 1 2 3
		int[] s1 = new int[len];
		int[] s2 = new int[len];
		int[] s3 = new int[len];

		// 1번 학생방식
		int cnt = 0;
		for (int i = 1; i <= 5; i++) {
			if(cnt==len) break;
			s1[cnt++] = i;
			//루프 리셋
			if(i==5) i=0;
		}
		
		//2번 방식
		for (int i = 0; i < len; i+=2) {
			s2[i] = 2;
		}
		cnt=1;
		for (int i = 1; i < len; i+=2) {
			if(cnt == 2) cnt = 3;
			else if(cnt == 6) cnt = 1; 
			s2[i] = cnt++;
		}
		//3번 방식
		cnt=0;
		int[] temp = {3,1,2,4,5};
		for (int i = 0; i < len; i+=2) {
			if(cnt == 5) cnt = 0; 
			s3[i] = temp[cnt];
			if(i+1 != len)
				s3[i+1] = temp[cnt];
			cnt++;
		}
		int s1c = check(s1,answers);
		int s2c = check(s2,answers);
		int s3c = check(s3,answers);
		
		ArrayList<Dot> list = new ArrayList<>();
		list.add(new Dot(1, s1c));
		list.add(new Dot(2, s2c));
		list.add(new Dot(3, s3c));
		
		list.sort(new Comparator<Dot>() {

			@Override
			public int compare(Dot o1, Dot o2) {
				return Integer.compare(o2.cnt, o1.cnt);
			}
		});
		
		int big = list.get(0).cnt;
		ArrayList<Integer> ans = new ArrayList<>();
		for (Dot dot : list) {
			if(dot.cnt == big)
				ans.add(dot.no);
		}
		
		int[] answer = new int[ans.size()];
		for (int i = 0; i < ans.size(); i++) {
			answer[i] = ans.get(i);
		}
		return answer;
	}
	
	private static int check(int[] s, int[] answers) {
		int cnt = 0;
		for (int i = 0; i < answers.length; i++) {
			if(s[i]==answers[i]) cnt++;
		}
		return cnt;
	}
}
