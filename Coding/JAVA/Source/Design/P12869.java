import java.util.Scanner;
import java.util.LinkedList;
import java.lang.Math;

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
    }
}

class P12869 
{
    public static int totalResult = Integer.MAX_VALUE;
    public static int[][][] DamageList;
    public static LinkedList<SCV> bfsList;

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