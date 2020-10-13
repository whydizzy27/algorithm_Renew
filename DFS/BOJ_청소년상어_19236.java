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

public class BOJ_청소년상어_19236 {
	static int max;
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,-1,-1,-1,0,1,1,1};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//번호저장
		int[][] map = new int[4][4];
		//방향저장
		int[][] dir = new int[4][4];
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				dir[i][j]=Integer.parseInt(st.nextToken())-1;
			}
		}
		
		//물고기 생존 여부
		boolean[] dead = new boolean[17];
		//상어 현위치
		int sx =0;
		int sy =0;
		int sdir = dir[0][0];
		int eat = map[0][0];
		//상어 있는 곳 을-1로둠
		map[0][0]=-1;
		dead[eat]=true;
		
//		for (int i = 0; i < 4; i++) {
//				System.out.println(Arrays.toString(dir[i]));
//		}
//		System.out.println("-------");
		dfs(sx, sy, sdir, eat, map, dir, dead);
		
		bw.write(String.valueOf(max));

		bw.flush();
		br.close();
		bw.close();
	}
	
	private static void dfs(int x, int y, int sdir, int cnt, int[][] map,int[][] dir, boolean[] dead) {
		
		
		//최대 세번
		int[][] tempmap = new int[4][4];
		int[][] tempdir = new int[4][4];
		boolean[] tempdead = new boolean[17];
		
		for (int i = 0; i < 4; i++) {
			tempmap[i] = map[i].clone();
			tempdir[i] = dir[i].clone();
		}
		tempdead = dead.clone();
		
		fishmove(tempmap, tempdir, tempdead);
		boolean check = false;
		for (int k = 1; k <= 3; k++) {
			int nx = x + k*dx[sdir];
			int ny = y + k*dy[sdir];
			
			if(nx<0 || nx>=4 || ny<0 || ny>=4||tempmap[nx][ny]==0 ) {
				continue;
			}
			
			check = true;
			int eatnum = tempmap[nx][ny];
			tempdead[eatnum]=true;
			tempmap[x][y]=0;
			tempmap[nx][ny]=-1;
			int eatdir = tempdir[nx][ny];
//			System.out.println(eatdir);
//			for (int i = 0; i < 4; i++) {
//				System.out.println(Arrays.toString(tempmap[i]));
//		}
//			System.out.println("-----------------------");
			dfs(nx,ny,eatdir,cnt+eatnum,tempmap,tempdir,tempdead);
			tempdead[eatnum]=false;
			tempmap[x][y]=-1;
			tempmap[nx][ny]=eatnum;
		}
		
		if(!check) {
			max = Math.max(max, cnt);
		}
		
		
	}

	private static void fishmove(int[][] map, int[][] dir, boolean[] dead) {
		//작은순으로 물고기찾기
		for (int k = 1; k < 17; k++) {
			//죽었으면 넘김
			if(dead[k]) continue;
			
			//물고기 찾기
			L:for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					//물고기 찾았을 시
					if(map[i][j]==k) {
						//현재 방향
						int curdir = dir[i][j];
						
						P:for (int r = 0; r < 8; r++) {
							int d = (curdir+r)%8;
							int nx = i + dx[d];
							int ny = j + dy[d];
							
							//상어나 인덱스 아웃
							if(nx<0 || nx>=4 || ny<0 || ny>=4 ||map[nx][ny]==-1) {
								continue;
							}
							
							int tempnum = map[nx][ny]; 
							int tempdir = dir[nx][ny]; 
							map[nx][ny]= k;
							dir[nx][ny]=d;
							map[i][j]=tempnum;
							dir[i][j]=tempdir;
							break P;
						}
						
						//물고기 찾았으니 break
						break L;
					}
				}
			}
		}
	}

	
}