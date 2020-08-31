package combination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_N과M4_15652 {
	static int N,M, temp[];
	static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		temp = new int[N];
		combination(0,1);
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void combination(int idx, int cur) throws IOException {
		if (idx==M) {
			for (int i = 0; i < M; i++) {
				bw.write(String.valueOf(temp[i])+" ");
			}
			bw.write("\n");
			
			return;
		}
		
		for (int i = cur; i <= N; i++) {
			temp[idx]=i;
			combination(idx+1,i);
		}
	}

}
