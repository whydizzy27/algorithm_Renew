package BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_안전영역_2468 {
	static int N, map[][], copy[][];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Dot {
		int x, y;

		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		copy = new int[N][N];
		int max = 0;
		int big = 0;
		int small = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				big = Math.max(big, map[i][j]);
				small = Math.min(small, map[i][j]);
			}
		}

//		System.out.println(small + " " + big);

		for (int k = small; k < big; k++) {
			int cnt = 0;

			copymap();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (copy[i][j] <= k)
						copy[i][j] = 0;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (copy[i][j] == 0)
						continue;
					bfs(new Dot(i, j));
					cnt++;
				}
			}
			max = Math.max(max, cnt);

		}
		bw.write(String.valueOf(Math.max(1, max)));

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Dot dot) {
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		copy[dot.x][dot.y] = 0;

		while (!q.isEmpty()) {
			Dot d = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N && copy[nx][ny] > 0) {
					copy[nx][ny] = 0;
					q.offer(new Dot(nx, ny));
				}

			}
		}
	}

	// 카피 맵
	private static void copymap() {
		for (int i = 0; i < N; i++) {
			copy[i] = map[i].clone();
		}
	}

}
