import java.util.Arrays;
import java.util.Comparator;

public class PRO_가장큰수 {

	public static void main(String[] args) {
		int[] numbers = {6, 10, 2};
		System.out.println(solution(numbers));
	}

	public static String solution(int[] numbers) {
		int len = numbers.length;
		String[] arr = new String[len];
		
		boolean isAllZero = false;
		for (int i = 0; i < len; i++) {
			if(numbers[i]!=0)
				isAllZero = true;
			arr[i] = String.valueOf(numbers[i]);
		}
		
		if(!isAllZero)
			return String.valueOf(0);
		
		Arrays.sort(arr, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return (o2+o1).compareTo(o1+o2);
			}
		});
		
		StringBuilder sb = new StringBuilder("");
		for (String string : arr) {
			sb.append(string);
		}
		
		return String.valueOf(sb);
	}

}
