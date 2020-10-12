package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_사다리조작_15684 {
	static int min = Integer.MAX_VALUE, N, M, H;
	static int[][] map;
	static boolean isOver;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 세로 선 수 , 가로선 수, 점선 수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			// 인덱스 0부터 둘것이기 때문
			map[a][b] = 1; // 오른쪽가지
			map[a][b + 1] = -1; // 왼쪽가지

		}

//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		// 3 보다 크면 -1
		if (isValid()) {
			min = 0;
		} else {
			for (int i = 1; i < 4; i++) {
				dfs(0, 0, 0, i);
			}
		}
		bw.write(min == Integer.MAX_VALUE ? "-1" : String.valueOf(min));

		bw.flush();
		br.close();
		bw.close();

	}

	private static void dfs(int x, int y, int cnt, int std) {
		if (isOver)
			return;
		if (cnt == std) {
			if (isValid()) {
				min = cnt;
				isOver = true;
			}
			return;
		}

		for (int j = y; j < N - 1; j++) {
			for (int i = x; i < H; i++) {
				if (map[i][j] != 0 || map[i][j+1]!=0)
					continue;
				map[i][j] = 1;
				map[i][j + 1] = -1;

//				for (int k = 0; k < H; k++) {
//					for (int r = 0; r < N; r++) {
//						System.out.print(map[k][r] + "  ");
//					}
//					System.out.println();
//				}
//				System.out.println("--------------");
				dfs(i + 1, j, cnt + 1, std);
				map[i][j] = 0;
				map[i][j + 1] = 0;
			}
			x = 0;
		}
	}

	private static boolean isValid() {

		for (int i = 0; i < N; i++) {
			int cur = i;
			for (int j = 0; j < H; j++) {
				cur += map[j][cur];
			}

			if (cur != i)
				return false;
		}
		return true;
	}

}
