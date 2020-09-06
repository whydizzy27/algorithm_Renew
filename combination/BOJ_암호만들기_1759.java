package combination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ_암호만들기_1759 {
	static int L,C;
	static int[] temp;
	static String[] str;
	static ArrayList<String> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		str = br.readLine().split(" ");
		//정렬 후 조합하면 알아서 사전순 정렬됨
		Arrays.sort(str);
		temp = new int[L];
		
		combination(0,0,0);
		
		for (String string : list) {
			bw.write(string + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
}

	private static void combination(int idx, int cur, int cnt) {
		if(idx == L) {
			if(cnt >= 1 && cnt <= L-2) {
				StringBuilder tmp = new StringBuilder();
				for (int i = 0; i < L; i++) {
					tmp.append(str[temp[i]]);
				}
				list.add(String.valueOf(tmp));
			}
			return;
		}
		
		for (int i = cur; i < C; i++) {
			int k=0;
			temp[idx]=i;
			if(str[i].equals("a") || str[i].equals("e") ||str[i].equals("i") ||str[i].equals("o") ||str[i].equals("u") ) {
				k=1;
			}
			combination(idx+1, i+1, cnt+k);
		}
	}

}
