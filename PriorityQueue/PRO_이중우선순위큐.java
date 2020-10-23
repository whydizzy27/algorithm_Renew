import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        
        PriorityQueue<Integer> maxq = new  PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Integer> minq = new  PriorityQueue<Integer>();
        
        for(int i=0;i<operations.length;i++){
            StringTokenizer st = new StringTokenizer(operations[i]);
            String s = st.nextToken();
            int val = Integer.parseInt(st.nextToken());
            
            //삽입
            if(s.equals("I")){
                minq.offer(val);
                maxq.offer(val);
            }
            //삭제
            else{
                if(maxq.isEmpty()) continue;
                //최대 삭제
                if(val==1){
                    //우선순위 큐에서 peek 사용 시 poll할 요소 미리 알 수 있음. peek도 힙정렬 결과다.
                    int max = maxq.peek();
                    //리스트에는 인덱스와 객체로 remove 할 수 있다. 큐는 인덱스로 삭제불가. 오직 객체로만 가능.
                    //따라서 랩핑해줄 필요가 없다. 랩핑의 이유가 인덱스로 삭제와 객체로 삭제를 구분용도였기 때문
                    maxq.remove(max);
                    minq.remove(max);
                }
                //최소 삭제
                else{
                    int min = minq.peek();
                    maxq.remove(min);
                    minq.remove(min);
                }
            }
        }
        
        
        int[] answer = {0,0};
        if(!maxq.isEmpty()){
            answer[0] = maxq.poll();
            answer[1] = minq.poll();
        }
        return answer;
    }
}
