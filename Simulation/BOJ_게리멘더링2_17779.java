package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_게리멘더링2_17779 {
	static int[][] person, gy;
	static int N, min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		person = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				person[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// x y d1 d2잡는 함수
		for (int x = 0; x < N-2; x++) {
			P:for (int y = 1; y < N-1; y++) {
				Q:for (int d1 = 1; d1 <= N - 2; d1++) {
					if(y-d1<0) continue P;
					for (int d2 = 1; d2 <= N - 2; d2++) {
						if(x+d1+d2 >= N || y+d2>=N) continue Q;
						gy = new int[N][N];
//						System.out.println(x+" "+y + " "+ d1 +" "+ d2);
						draw(x, y, d1, d2);
						calc();
					}
				}
			}
		}
		// 구역 맵 그리는 함수
		// 구열별 합구하고 차이 구하는 함수
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(gy[i]));
//		}

		bw.write(String.valueOf(min));

		bw.flush();
		br.close();
		bw.close();
	}

	private static void draw(int x, int y, int d1, int d2) {
		// 5번 테두리 그리기
		for (int i = 0; i <= d1; i++) {
			gy[x + i][y - i] = 5;
			gy[x + d2 + i][y + d2 - i] = 5;
		}
		for (int i = 0; i <= d2; i++) {
			gy[x + i][y + i] = 5;
			gy[x + d1 + i][y - d1 + i] = 5;
		}

		// 1번
		L: for (int i = 0; i <= x + d1 - 1; i++) {
			for (int j = 0; j <= y; j++) {
				if (gy[i][j] != 0)
					continue L;
				gy[i][j] = 1;
			}
		}
		// 2번
		L: for (int i = 0; i <= x+d2; i++) {
			for (int j = N-1; j >= y; j--) {
				if (gy[i][j] != 0)
					continue L;
				gy[i][j] = 2;
			}
		}
		// 3번
		L: for (int i = x+d1-1; i < N; i++) {
			for (int j = 0; j <= y-d1+d2-1; j++) {
				if (gy[i][j] != 0)
					continue L;
				gy[i][j] = 3;
			}
		}
		// 4번
		L: for (int i = x+d2; i < N; i++) {
			for (int j = N-1; j >= y-d1+d2; j--) {
				if (gy[i][j] != 0)
					continue L;
				gy[i][j] = 4;
			}
		}

		// 5번 속 채우기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (gy[i][j] == 0)
					gy[i][j] = 5;
			}
		}
	}

	private static void calc() {

		int[] count = new int[6];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				count[gy[i][j]] += person[i][j];
			}
		}

		int minn = Integer.MAX_VALUE;
		int maxx = Integer.MIN_VALUE;

		for (int i = 1; i <= 5; i++) {
			minn = Math.min(minn, count[i]);
			maxx = Math.max(maxx, count[i]);
		}

		min = Math.min(min, maxx - minn);
	}

}