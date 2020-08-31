package permutation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_N과M3_15651 {
	static int N,M, temp[];
	static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		temp = new int[N];
		permutation(0);
		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void permutation(int idx) throws IOException {
		if (idx==M) {
			for (int i = 0; i < M; i++) {
				bw.write(String.valueOf(temp[i])+" ");
			}
			bw.write("\n");
			
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			temp[idx]=i;
			permutation(idx+1);
		}
	}

}
