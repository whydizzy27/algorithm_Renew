import java.util.*;

class Solution {
    static boolean[] visited, p_visited,isPrime;
    static int len,M, cnt,temp[];
    static String str;
    static LinkedList<Character> list;
    static HashSet<Integer> set;
    public int solution(String numbers) {
        len = numbers.length();
        str = numbers;
        visited = new boolean[len];
        set = new HashSet<>();
        
        subset(0);
        return cnt;
    }
    
    private static void subset(int idx){
        if(idx==len){
            list = new LinkedList<>();
            boolean chk = false;
            for(int i=0;i<len;i++){
                if(!visited[i]) continue;
                list.add(str.charAt(i));
                chk=true;
            }
            
            if(!chk) return;
            
            M = list.size();
            p_visited = new boolean[M];
            temp = new int[M];
            permutation(0);
            
            return;
        }
        
        visited[idx]=true;
        subset(idx+1);
        visited[idx]=false;
        subset(idx+1);
        
    }
    
    private static void permutation(int idx){
        if(idx==M){
            String s = "";
            for(int i=0;i<M;i++){
                s += list.get(temp[i]);
            }
            
            int val = Integer.parseInt(s);
            // System.out.println(val);
            //¼Ò¼öÆÇº°
            if(isPrime(val) && set.add(val)) cnt++;
            
            return;
        }
        
        for(int i=0;i<M;i++){
            if(p_visited[i]) continue;
            p_visited[i]=true;
            temp[idx]=i;
            permutation(idx+1);
            p_visited[i]=false;
        }
        
    }
    
    private static boolean isPrime(int n) {

		int sqrt = (int) Math.sqrt(n);

		// 2´Â À¯ÀÏÇÑ Â¦¼ö ¼Ò¼ö
		if (n == 2)
			return true;

		// Â¦¼ö¿Í 1Àº ¼Ò¼ö°¡ ¾Æ´Ô
		if (n % 2 == 0 || n == 1)
			return false;

		// Á¦°ö±Ù±îÁöÀÇ È¦¼ö·Î ³ª´²º¸¸é µÊ
		for (int i = 3; i <= sqrt; i += 2) {
			if (n % i == 0)
				return false;
		}

		return true;
	}
}