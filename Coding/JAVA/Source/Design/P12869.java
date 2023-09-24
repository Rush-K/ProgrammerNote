import java.util.Scanner;
import java.util.LinkedList;
import java.util.Arrays;
import java.lang.Math;

/* 
 * 문제 출처) 백준온라인저지 12869번 뮤탈리스크 
 * 
 * 핵심 요약) BFS & DP(Memoization)
 * 
 * 핵심 설명) 1. SCV 마리 수에 맞게 HP 너비 우선 탐색 (최소 공격 횟수이므로) 
 *  
 *          2. 동일한 HP는 탐색할 필요가 없으므로 Memoization으로 탐색 유무를 기록 후, 필터링 
 *          
*/

// SCV Class (HP와 공격 당한 횟수)
class SCV
{
    int[] HP;
    int result;

    public SCV(int[] HP, int result)
    {
        int len = 0;
        for (int i = 0; i < HP.length; i++) if (HP[i] > 0) len++;
        this.HP = new int[len];
        len = 0;
        for (int i = 0; i < HP.length; i++) if (HP[i] > 0) this.HP[len++] = HP[i];
        this.result = result;
        Arrays.sort(this.HP);
    }
}

class P12869 
{
    public static int totalResult = Integer.MAX_VALUE;
    public static int[][][] DamageList;
    public static LinkedList<SCV> bfsList;
    public static int[][][] VisitedList;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int[] HP = new int[N];
        int result = 0;

        for (int i = 0; i < N; i++) HP[i] = sc.nextInt();
        
        DamageList = new int[4][][];
        DamageList[1] = new int[][]{{9}};
        DamageList[2] = new int[][]{{9, 3}, {3, 9}};
        DamageList[3] = new int[][]{{1,3,9}, {1,9,3},
                                     {3,1,9}, {3,9,1},
                                     {9,1,3}, {9,3,1}};

        VisitedList = new int[61][61][61];
        bfsList = new LinkedList<SCV>();
        bfsList.add(new SCV(HP, 0));

        bfs();

        System.out.println(totalResult);

        return;
    }

    public static void bfs()
    {
        while (!bfsList.isEmpty())
        {
            SCV scvStatus = bfsList.poll();
            
            if (scvStatus.HP.length == 3) // 탐색 기록 & 이미 탐색한 부분은 SKIP
            {
                if (VisitedList[scvStatus.HP[0]][scvStatus.HP[1]][scvStatus.HP[2]] == 0)
                {
                    VisitedList[scvStatus.HP[0]][scvStatus.HP[1]][scvStatus.HP[2]] = 1;
                }
                else continue;
            }
            else if (scvStatus.HP.length == 2)
            {
                if (VisitedList[0][scvStatus.HP[0]][scvStatus.HP[1]] == 0)
                {
                    VisitedList[0][scvStatus.HP[0]][scvStatus.HP[1]] = 1;
                }
                else continue;
            }
            else if (scvStatus.HP.length == 1)
            {
                if (VisitedList[0][0][scvStatus.HP[0]] == 0)
                {
                    VisitedList[0][0][scvStatus.HP[0]] = 1;
                }
                else continue;
            }

            for (int index = 0; index < DamageList[scvStatus.HP.length].length; index++)
            {
                for (int i = 0; i < scvStatus.HP.length; i++) scvStatus.HP[i] -= DamageList[scvStatus.HP.length][index][i];
                scvStatus.result++;

                if (CheckHP(scvStatus.HP)) 
                {
                    totalResult = Math.min(totalResult, scvStatus.result); 
                    return;
                }
                else bfsList.add(new SCV(scvStatus.HP, scvStatus.result));

                scvStatus.result--;
                for (int i = 0; i < scvStatus.HP.length; i++) scvStatus.HP[i] += DamageList[scvStatus.HP.length][index][i];
            }
        }
    }

    public static boolean CheckHP(int[] HP)
    {
        for (int i = 0; i < HP.length; i++)
        {
            if (HP[i] > 0) return false;
        }
        return true;
    }
}