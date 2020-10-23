import java.util.*;

class Solution {
    static int current;
    static class Dot implements Comparable<Dot>{
        int start, duration;
        public Dot(int start, int duration){
            this.start = start;
            this.duration = duration;
        }
        
        @Override
        public int compareTo(Dot d){
            
                return Integer.compare(this.duration,d.duration);
            
        }
    }
    public int solution(int[][] jobs) {
        
        PriorityQueue<Dot> q = new PriorityQueue<>();
        LinkedList<Dot> list = new LinkedList<>();
        for(int i=0; i<jobs.length; i++){
            list.add(new Dot(jobs[i][0],jobs[i][1]));
        }
        
        list.sort(new Comparator<Dot>(){
            @Override
            public int compare(Dot d1, Dot d2){
                return Integer.compare(d1.start,d2.start);
            }
        });
        
        int sum = 0;
        while(true){
            int len = list.size();
            // System.out.println("len:"+len);
            if(len==0 && q.size()==0) break;
            if(len!=0){
                while(true){
                    len = list.size();
                    if(len>0){
                        if(list.get(0).start <= current)
                             q.offer(list.remove(0));
                        else
                            break;
                    }

                    else
                        break;
                }
            
            }
            Dot d = q.poll();
            if(d!=null){
                current += d.duration;
                // System.out.println(sum);
                sum += current - d.start;
            }
            else{
                current++;
            }
            
        }
       
        return sum / jobs.length;
    }
}
