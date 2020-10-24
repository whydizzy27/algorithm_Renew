package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_ACMCraft_1005 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tn = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < tn; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] cost = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				cost[i]=Integer.parseInt(st.nextToken());
			}
			
			ArrayList<Integer>[] list = new ArrayList[N+1];
			for (int i = 1; i <= N; i++) {
				list[i] = new ArrayList<>();
			}
			
			int[] indegree = new int[N+1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list[x].add(y);
				indegree[y]++;
			}
			int target = Integer.parseInt(br.readLine());
			
			Queue<Integer> q = new LinkedList<>();
			for (int i = 1; i <= N; i++) {
				if(indegree[i]==0) q.offer(i);
			}
			
			int[] time = new int[N+1];
			time = cost.clone();

			while(!q.isEmpty()) {
				int cur = q.poll();
//				System.out.println(cur);
				for(int next : list[cur]) {
					indegree[next]--;
					time[next] = Math.max(time[next],cost[next] + time[cur]); 
					if(indegree[next]==0) q.offer(next);
				}
			}
			
//			System.out.println(Arrays.toString(time));
			
			bw.write(String.valueOf(time[target])+"\n");
			
			
		}

		bw.flush();
		br.close();
		bw.close();
	}
	
}