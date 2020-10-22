import java.util.*;

class Solution {
    static class Dot implements Comparable<Dot>{
        int vertex;
        int value;
        
        public Dot(int vertex, int value){
            this.vertex = vertex;
            this.value = value;
        }
        
        @Override
        public int compareTo(Dot d){
            return Integer.compare(this.value, d.value);
        }
        
    }
    public int solution(int n, int[][] edge) {
        
        ArrayList<Dot>[] list = new ArrayList[n+1];
        
        for(int i=1;i<=n; i++){
            list[i] = new ArrayList<Dot>();
        }
        
        int[] distance = new int[n+1];
        boolean[] visited = new boolean[n+1];
        
        for(int i=0;i<edge.length; i++){
            list[edge[i][0]].add(new Dot(edge[i][1],1));
            list[edge[i][1]].add(new Dot(edge[i][0],1));
        }
        
        
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        distance[1] = 0;
        
        PriorityQueue<Dot> q = new PriorityQueue<Dot>();
        q.offer(new Dot(1,0));
        
        while(!q.isEmpty()){
            Dot current = q.poll();
            
            if(visited[current.vertex]) continue;
            
            visited[current.vertex] = true;
            
            for(Dot d : list[current.vertex]){
                int next = d.vertex;
                int value = d.value;
                
                if(!visited[next] && distance[next] > distance[current.vertex] + value){
                    distance[next] = distance[current.vertex] + value;
                    q.offer(new Dot(next, distance[next]) );
                }
                
                
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int i=1;i<=n; i++){
            max = Math.max(max,distance[i]);
        }
        int cnt=0;
        for(int i=1;i<=n; i++){
            if(max==distance[i]) cnt++;

        }
        
        
        
        return cnt;
    }
}