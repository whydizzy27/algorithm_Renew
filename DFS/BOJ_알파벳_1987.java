package DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_알파벳_1987 {
	static char map[][];
	static int max, N, M;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[26]; // 알파벳 개수
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		dfs(0, 0, 1);

		bw.write(String.valueOf(max) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int x, int y, int cnt) {
		if (max == 26)
			return;
		if (cnt > max)
			max = cnt;

		visited[map[x][y] - 'A'] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
				if (visited[map[nx][ny] - 'A'])
					continue;
				dfs(nx, ny, cnt + 1);

			}
		}

		visited[map[x][y] - 'A'] = false;
	}

}
