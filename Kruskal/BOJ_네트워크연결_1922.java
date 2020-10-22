package temp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*MST 구할 때 쓰는 알고리즘 중 하나
크루스칼 알고리즘 (유니온파인드 응용- 패스 컴프레션까지 적용된 소스. 효율 up)
간적크 간많프
크루스칼은 간선 중심(간선리스트 사용)
프림은 정점 중심. 다익스트라도 마찬가지.*/
public class BOJ_네트워크연결_1922 {
	
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		//간선 리스트 생성 ( 간선리스트는 배열을 사용 )
		int[][] arr = new int[M][3]; //시작점, 종점, cost
		for (int i = 0; i < M; i++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
			arr[i][2]=Integer.parseInt(st.nextToken());
		}
		
		//MST 제작을 위한 cost 낮은 순위 정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		int mincost = 0; //최소 비용
		int cnt = 0; //효율성 증가를 위한 탈출 조건
		parents = new int[N+1]; //대표자 저장 배열
		Arrays.fill(parents, -1); //초기에 전부 -1로 세팅. -1이면 그 정점의 대표자라는 의미. 처음엔 자신이 자신의 대표자임
		//유니온 파인드 과정
		for (int i = 0; i < M; i++) {
			if(cnt == N-1) break; //MST 특징 : 정점 N개면 간선 N-1개다. 간선이 N-1개라는 것은 MST가 완성되었다는 뜻
			
			if(!union(arr[i][0],arr[i][1]))
				continue;
			
			mincost += arr[i][2];
			cnt++;
		}
		
		bw.write(String.valueOf(mincost));

		bw.flush();
		br.close();
		bw.close();
	}

	private static boolean union(int x, int y) {
		//두 정점의 대표자 정점 번호를 찾는 것이 find함수
		int xRoot = find(x);
		int yRoot = find(y);
		
		//두 대표자가 같다는 것은 이미 같은 집합이라는 뜻. 고로 MST에 해당 간선은 포함시키지 않음
		//달라야 포함시킴
		if(xRoot != yRoot) {
			parents[yRoot] = xRoot;
			return true;
		}
		//두 대표자 번호가 같을 때
		return false;
	}

	private static int find(int x) {
		//parents 값이 -1이면 그것이 곧 대표자다
		if(parents[x]<0)
			return x;
		
		//같은 집합이 모두 같은 대표자를 갖게 하는 path compression 최적화 적용
		//find와 동시에 백트래킹 시 대표자 갱신
		return parents[x] = find(parents[x]);
	}
	
}