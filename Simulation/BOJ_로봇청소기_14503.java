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

public class BOJ_로봇청소기_14503 {

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int sx = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					visited[i][j] = true;
			}
		}

		visited[sx][sy] = true;
		int cnt = 1;
		while (true) {

			int left = (dir + 3) % 4;

			if (visited[sx + dx[0]][sy + dy[0]] && visited[sx + dx[1]][sy + dy[1]] && visited[sx + dx[2]][sy + dy[2]]
					&& visited[sx + dx[3]][sy + dy[3]]) {
				int back = (dir + 2) % 4;
				if (map[sx + dx[back]][sy + dy[back]] == 1) {
					break;
				} else {
					sx += dx[back];
					sy += dy[back];
				}
			} else if (!visited[sx + dx[left]][sy + dy[left]]) {
				sx += dx[left];
				sy += dy[left];
				dir = left;
				visited[sx][sy] = true;
				cnt++;
			} else if (visited[sx + dx[left]][sy + dy[left]]) {
				dir = left;
			}
		}
		bw.write(String.valueOf(cnt));

		bw.flush();
		br.close();
		bw.close();
	}

}