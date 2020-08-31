package BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_유기농배추_1012 {
	static int N, M, map[][],cnt;
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

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < tn; tc++) {
			cnt=0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				map[x][y]=1;
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j]!=1)continue;
					bfs(new Dot(i, j));
					cnt++;
				}
			}

			bw.write(String.valueOf(cnt)+"\n");

		}
		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Dot dot) {
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		map[dot.x][dot.y] = 2;
		
		while (!q.isEmpty()) {
			Dot d = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M)
					if (map[nx][ny] == 1) {
						map[nx][ny] = 2;
						q.offer(new Dot(nx, ny));
					}
			}

		}

	}

}
