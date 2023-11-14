import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

/* 
 * 문제 출처) 프로그래머스 현대모비스 예선 : 상담원 인원
 * 
 * 핵심 요약) Priority Queue & DFS
 * 
 * 핵심 설명) 1. 상담 유형 별로 Priority Queue 생성 후 상담 시작 시간 우선 순위로 삽입 
 *  
 *          2. 각 유형별 최대 배치 가능 상담원까지 시간 계산
 *
 *          3. 시간의 합이 최소가 되는 케이스를 DFS로 도출 
 *          
*/
public class PG_NumOfConsultant
{
    public ArrayList<PriorityQueue<Integer[]>> scheduler;
    public int answer = Integer.MAX_VALUE;
    
    public int solution(int k, int n, int[][] reqs) {
        Integer[][] convertReqs = new Integer[reqs.length][3];
        
        for (int i = 0; i < reqs.length; i++)
        {
            for (int j = 0; j < 3; j++) convertReqs[i][j] = reqs[i][j];
        }
        
        scheduler = new ArrayList<PriorityQueue<Integer[]>>();
        
        for (int i = 0; i <= k; i++) 
            scheduler.add(new PriorityQueue<Integer[]>(new Comparator<Integer[]>() {
                @Override 
                public int compare(Integer[] o1, Integer[] o2)
                {
                    return o1[0] >= o2[0] ? 1 : -1;
                }
            }));
        
        for (int i = 0; i < convertReqs.length; i++)
            scheduler.get(convertReqs[i][2]).add(convertReqs[i]);
        
        int[][] resultTable = new int[k + 1][n - k + 2];
        
        for (int i = 1; i < scheduler.size(); i++)
        {
            for (int j = 1; j < n - k + 2; j++)
            {
                resultTable[i][j] = getTotalWaitTime(scheduler.get(i), j);
            }
        }
        
        for (int i = 1; i < k + 1; i++)
        {
            for (int j = 1; j < n - k + 2; j++)
            {
                System.out.print(resultTable[i][j] + " ");
            }
            System.out.println();
        }
        
        getResult(resultTable, n);
        
        return answer;
    }
    
    public int getTotalWaitTime(PriorityQueue<Integer[]> consult, int numOfConsultant)
    {
        int waitTime = 0;
        
        PriorityQueue<Integer[]> backUp = new PriorityQueue<Integer[]>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2)
            {
                return o1[0] >= o2[0] ? 1 : -11;
            }
        });
        
        Integer[] consultants = new Integer[numOfConsultant];
        for (int i = 0; i < consultants.length; i++) consultants[i] = 0;
        
        while (!consult.isEmpty())
        {
            Integer[] menti = consult.poll();
            
            int freeConsultant = getFreeConsultant(consultants, menti[0]);
            //System.out.println("free : " + freeConsultant);
            if (freeConsultant == -1)
            {
                int leastTimeConsultant = getLeastTimeConsultant(consultants, menti[0]);
                waitTime += consultants[leastTimeConsultant] - menti[0];
                consultants[leastTimeConsultant] += menti[1]; 
            }
            else
            {
                consultants[freeConsultant] = menti[0] + menti[1];
            }
            
            backUp.add(menti);
        }
        
        while (!backUp.isEmpty()) consult.add(backUp.poll());
        
        return waitTime;
    }
    
    public int getFreeConsultant(Integer[] consultants, Integer mentiStartTime)
    {
        for (int i = 0; i < consultants.length; i++)
        {
            //System.out.println(consultants[i] + ":" + mentiStartTime);
            if (consultants[i] <= mentiStartTime)
            {
                return i;
            }
        }
        
        return -1;
    }
    
    public int getLeastTimeConsultant(Integer[] consultants, Integer mentiStartTime)
    {
        int result = 0;
        int MAX = Integer.MAX_VALUE;
        
        for (int i = 0; i < consultants.length; i++)
        {
            if (consultants[i] - mentiStartTime < MAX)
            {
                MAX = consultants[i] - mentiStartTime;
                result = i;
            }
        }
        
        return result;
    }
    
    public void getResult(int[][] resultTable, int n)
    {
        int np = 1;
        int nc = n;
        int value = 0;
        
        dfs(resultTable, np, nc, value);
    }
    
    public void dfs(int[][] result, int np, int nc, int value)
    {
        if (nc == 0 && np > result.length - 1) {
            answer = Math.min(answer, value);    
            return;
        }
        
        if (np > result.length - 1) return;
        
        for (int i = 1; i < result[np].length; i++)
        {
            if (nc - i >= 0) dfs(result, np + 1, nc - i, value + result[np][i]);
            //else continue;
        }
    }
}