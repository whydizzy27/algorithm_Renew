package BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_적록색약_10026 {
	static int N, jry, jrn;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Dot {
		int x, y;

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		// 적록 no
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j])
					continue;
				bfs(new Dot(i, j));
				jrn++;
			}
		}

		// 적록맵 변경
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]=='R') 
				map[i][j] = 'G';
			}
		}
		
		visited = new boolean[N][N];

		// 적록 yes
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j])
					continue;
				bfs(new Dot(i, j));
				jry++;
			}
		}
		bw.write(String.valueOf(jrn + " " + jry));

		bw.flush();
		br.close();
		bw.close();
	}

	private static void bfs(Dot dot) {
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		visited[dot.x][dot.y] = true;
		char std = map[dot.x][dot.y];

		while (!q.isEmpty()) {
			Dot d = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N)
					if (map[nx][ny] == std && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Dot(nx, ny));
					}
			}

		}

	}

}
