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

public class BOJ_스타트택시_19238 {
	static int N, M, K, map[][], sx, sy, loc[][];
	static boolean[][] start;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Dot {
		int x, y, cost;

		public Dot(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		start = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					map[i][j] = -1;
			}
		}

		st = new StringTokenizer(br.readLine());
		// 현 택시위치
		sx = Integer.parseInt(st.nextToken()) - 1;
		sy = Integer.parseInt(st.nextToken()) - 1;
		loc = new int[M + 1][4];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = i;
			start[x][y] = true;

			int e_x = Integer.parseInt(st.nextToken()) - 1;
			int e_y = Integer.parseInt(st.nextToken()) - 1;
			loc[i][0] = x;
			loc[i][1] = y;
			loc[i][2] = e_x;
			loc[i][3] = e_y;
		}

		int ans = 0;
		int ridecus = 0;
		int cnt = 0;
		// 운행 시작
		while (cnt != M) {
			// 스타트지점과 고객위치 다를 시
			if (!start[sx][sy]) {

				// 가장가까운 승객 태우기
				ridecus = whoride(new Dot(sx, sy, 0));
				start[sx][sy] = false;
				if (K <= 0 || (ridecus < 0 && cnt != M)) {
					ans = -1;
					break;
				}

				// 목적지 찾아가기
				if (gohome(new Dot(sx, sy, 0), ridecus)<=0) {
					ans = -1;
					break;
				}

			} else {
				start[sx][sy] = false;
				// 바로목적지찾기
				if (gohome(new Dot(sx, sy, 0), map[sx][sy])<=0) {
					ans = -1;
					break;
				}
			}

			cnt++;
			ans = K;
		}

		bw.write(String.valueOf(ans));

		bw.flush();
		br.close();
		bw.close();
	}

	// 누구 태울지
	private static int whoride(Dot dot) {
		visited = new boolean[N][N];
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		visited[dot.x][dot.y] = true;

		int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE, cost = 0, who = 0;
		while (!q.isEmpty()) {
			int qsize = q.size();

			while (qsize-- > 0) {
				Dot d = q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = d.x + dx[i];
					int ny = d.y + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == -1)
						continue;

					if (map[nx][ny] >= 0 && !start[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Dot(nx, ny, d.cost + 1));
					} else if (map[nx][ny] > 0 && start[nx][ny]) {
						visited[nx][ny] = true;

						if (nx < minx) {
							minx = nx;
							miny = ny;
							cost = d.cost + 1;
							who = map[nx][ny];
						} else if (nx == minx) {
							if (ny < miny) {
								miny = ny;
								who = map[nx][ny];
							}
						}
					}
				}
			}

			//
			if (minx != Integer.MAX_VALUE) {
				sx = minx;
				sy = miny;
				K -= cost;
				return who;
			}
		}

		return -1;
	}

	// 집 찾아가기
	private static int gohome(Dot dot, int tar) {
		visited = new boolean[N][N];
		Queue<Dot> q = new LinkedList<>();
		q.offer(dot);
		visited[dot.x][dot.y] = true;

		int cost = Integer.MAX_VALUE;
		L: while (!q.isEmpty()) {

			Dot d = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == -1)
					continue;

				if (nx == loc[tar][2] && ny == loc[tar][3]) {
					sx = nx;
					sy = ny;
					cost = d.cost + 1;
					K -= cost;
					break L;
				} else {
					visited[nx][ny] = true;
					q.offer(new Dot(nx, ny, d.cost + 1));
				}
			}

		}

		if (cost == Integer.MAX_VALUE) {
			return -1;
		} else {

			if (K < 0) {
				return 0;
			} else {
				K += cost * 2;
				return 1;
			}
		}
	}

}