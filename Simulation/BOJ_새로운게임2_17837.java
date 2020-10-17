package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_새로운게임2_17837 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] loc;
	static int K, N;
	static LinkedList<Integer>[][] map;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[][] color = new int[N][N];
		map = new LinkedList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new LinkedList<Integer>();
			}
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				color[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		loc = new int[K + 1][3];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			loc[i][0] = Integer.parseInt(st.nextToken()) - 1;
			loc[i][1] = Integer.parseInt(st.nextToken()) - 1;
			loc[i][2] = Integer.parseInt(st.nextToken()) - 1;

			map[loc[i][0]][loc[i][1]].add(i);
		}
//		for (int i = 0; i < loc.length; i++) {
//			System.out.println(Arrays.toString(loc[i]));
//		}
		int turn = 1;

		FIN: while (true) {
//			System.out.println(turn);
			boolean isOver = false;
//			System.out.println(turn);
			// 말 이동
			// k:말번호
			for (int k = 1; k <= K; k++) {
				// 현재 좌표 ,방향
				int x = loc[k][0];
				int y = loc[k][1];
				int d = loc[k][2];

				// 이동할 좌표
				int nx = x + dx[d];
				int ny = y + dy[d];

				// 좌표 나가거나 파란색
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || color[nx][ny] == 2) {
					// 밑에거 + 방향 변환
//					loc[k][2]= ()

					// 먼저 방향 전환후 한칸의 위치 탐색
					nx -= dx[d];
					ny -= dy[d];

					if (d == 0)
						d = 1;
					else if (d == 1)
						d = 0;
					else if (d == 2)
						d = 3;
					else if (d == 3)
						d = 2;
					nx += dx[d];
					ny += dy[d];

					// 반대편에도 범위밖이나 파란색이라면
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || color[nx][ny] == 2) {
						loc[k][2]=d;
//						for (int i = 0; i < N; i++) {
//							System.out.println(map[0][i]);
//						}
						if (checkall(x, y))
							break FIN;
					}
					//반대편이 흰이나 빨이면
					else {
						if (color[nx][ny] == 0) {
							boolean isOut = false;
							// 기존 거에서 꺼내기
							for (int i = 0; i < map[x][y].size(); i++) {
								if (map[x][y].get(i) == k || isOut) {
									int val = map[x][y].remove(i);
									map[nx][ny].add(val);
									loc[val][0] = nx;
									loc[val][1] = ny;
									isOut = true;
									i--;
								}
							}
							isOver = true;
//							for (int i = 0; i < N; i++) {
//								System.out.println(map[0][i]);
//							}
							loc[k][2]=d;
							if (checkall(nx, ny))
								break FIN;

						}
						// 빨강
						else if (color[nx][ny] == 1) {
							boolean isOut = false;
							// 기존 거에서 꺼낸후 반대로 넣기
							Stack<Integer> stack = new Stack<>();
							for (int i = 0; i < map[x][y].size(); i++) {
								if (map[x][y].get(i) == k || isOut) {
									stack.push(map[x][y].remove(i));
									isOut = true;
									i--;
								}
							}

							while (!stack.isEmpty()) {
								int val = stack.pop();
								loc[val][0] = nx;
								loc[val][1] = ny;
								map[nx][ny].add(val);
							}
							isOver = true;
//							for (int i = 0; i < N; i++) {
//								System.out.println(map[0][i]);
//							}
							loc[k][2]=d;
							if (checkall(nx, ny))
								break FIN;
						}
//						boolean isOut = false;
//						for (int i = 0; i < map[x][y].size(); i++) {
//							if (map[x][y].get(i) == k || isOut) {
//								int val = map[x][y].remove(i);
//								map[nx][ny].add(val);
//								loc[val][0] = nx;
//								loc[val][1] = ny;
//								isOut = true;
//								i--;
//							}
//						}
//						loc[k][2]=d;
//						isOver = true;
////						for (int i = 0; i < N; i++) {
////							System.out.println(map[0][i]);
////						}
//						if (checkall(nx, ny))
//							break FIN;
					}

				}
				// 다음 이동칸 흰색
				else if (color[nx][ny] == 0) {
					boolean isOut = false;
					// 기존 거에서 꺼내기
					for (int i = 0; i < map[x][y].size(); i++) {
						if (map[x][y].get(i) == k || isOut) {
							int val = map[x][y].remove(i);
							map[nx][ny].add(val);
							loc[val][0] = nx;
							loc[val][1] = ny;
							isOut = true;
							i--;
						}
					}
					isOver = true;
//					for (int i = 0; i < N; i++) {
//						System.out.println(map[0][i]);
//					}
					if (checkall(nx, ny))
						break FIN;

				}
				// 빨강
				else if (color[nx][ny] == 1) {
					boolean isOut = false;
					// 기존 거에서 꺼낸후 반대로 넣기
					Stack<Integer> stack = new Stack<>();
					for (int i = 0; i < map[x][y].size(); i++) {
						if (map[x][y].get(i) == k || isOut) {
							stack.push(map[x][y].remove(i));
							isOut = true;
							i--;
						}
					}

					while (!stack.isEmpty()) {
						int val = stack.pop();
						loc[val][0] = nx;
						loc[val][1] = ny;
						map[nx][ny].add(val);
					}
					isOver = true;
//					for (int i = 0; i < N; i++) {
//						System.out.println(map[0][i]);
//					}
					if (checkall(nx, ny))
						break FIN;
				}

//				System.out.println(k + " " + loc[k][0] + " " + loc[k][1] + " " + loc[k][2]);

//				for (int i = 1; i <= K; i++) {
//					if (loc[i][2] == 0) {
//						System.out.print(i+"right ");
//						
//					} else if (loc[i][2] == 1) {
//						System.out.print(i+"left ");
//
//					} else if (loc[i][2] == 2) {
//						System.out.print(i+"up ");
//
//					} else if (loc[i][2] == 3) {
//						System.out.print(i+"down ");
//
//					}
//				}
//				System.out.println();
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < N; j++) {
//						System.out.print(map[i][j] + "  ");
//					}
//					System.out.println();
//				}
//				System.out.println("----------------------");
			}
			// 1000보다 턴 크거나 게임 무한 지속 시 -1 출력
			if (turn > 1000 || !isOver) {
				turn = -1;
				break;
			}

			turn++;
		}
		bw.write(String.valueOf(turn));

		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean checkall(int i, int j) {
		int cnt = 0;
		if (map[i][j].size() >= 4)
			return true;
		return false;
	}
}