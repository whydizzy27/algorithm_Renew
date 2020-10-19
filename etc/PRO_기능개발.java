import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        LinkedList<Integer> list = new LinkedList<>();
        int len = progresses.length;
        boolean[] visited = new boolean[len];
        int idx = 0;
        boolean isOver=false;
        while(!isOver){
            //1일 업무 추가
            for(int i=0;i<len;i++){
                if(visited[i]) continue;
                progresses[i]+=speeds[i];
                if(progresses[i]>=100)
                    visited[i]=true;
            }
            
            //배포 수 세기
            int cnt=0;
            for(int i=idx;i<len;i++){
                if(!visited[i]) {
                    idx=i;
                    break;
                }
                if(i==len-1){
                    isOver = true;
                }
                cnt++;
            }
            
            if(cnt==0) continue;
            list.add(cnt);
            
            
        }
        
        int[] answer = new int[list.size()];
        int d = 0;
        for(int k : list){
            answer[d]=k;
            d++;
        }
        return answer;
    }
}