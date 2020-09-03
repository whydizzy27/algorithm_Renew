package PriorityQueue;

import java.util.*;


class Solution {
   
    public int solution(int[] priorities, int location) {
        // 결과물이 내림차순과 같음
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        //우선순위 큐에 넣음
        for(int k : priorities){
            q.offer(k);
        }

        //몇번째인가
        int num = 1;
        
        while(!q.isEmpty()){
            for(int i=0;i<priorities.length; i++){
                 if(priorities[i]==q.peek()){
                     //찾고자하는 기존 위치와 배열 인덱스 같으면
                     if(i==location){
                         return num;
                     }
                     q.poll();
                     num++;
                 }
                
            }
        }
        //형식상
        return num;

    }
}
