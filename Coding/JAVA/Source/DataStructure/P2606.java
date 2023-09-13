package Coding.JAVA.Source.DataStructure;

import java.util.Scanner;

/* 
 * 문제 출처) 백준온라인저지 2606번 곱셈
 * 
 * 핵심 요약) DFS
 * 
 * 핵심 설명) 1. 컴퓨터 네트워크를 그래프(2차원 배열)로 표현
 *  
 *          2. 해당 그래프 깊이 우선 탐색 (재귀)
 *          
*/
public class P2606
{
    public static int N;
    public static int L;
    public static int[][] MAP;
    public static int[] NODES;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        MAP = new int[N + 1][N + 1];
        NODES = new int[N + 1];

        for (int i = 0; i < L; i++) 
        {
            int src, dest;
            src = sc.nextInt();
            dest = sc.nextInt();

            MAP[src][dest] = 1;
            MAP[dest][src] = 1;
        }

        NODES[1] = 1;

        dfs(1);

        int result = 0;

        for (int i = 1; i < NODES.length; i++) if (NODES[i] == 1) result++;
        System.out.println(result - 1);
    }

    public static void dfs(int NODE)
    {
        for (int i = 1; i < N + 1; i++)
        {
            if (MAP[NODE][i] == 1)
            {
                MAP[NODE][i] = 2;
                NODES[i] = 1;
                dfs(i);
            }
            else if (MAP[NODE][i] == 2) return;
        }

        return;
    }
}