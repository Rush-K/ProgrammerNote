import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* 
 * 문제 출처) 백준온라인저지 2579번 계단 오르기
 * 
 * 핵심 요약) DP(Memoization)
 * 
 * 핵심 설명) 1. 계단을 오를 수 있는 경우 SCORE[N] = SCORE[N - 2] + STAIR[N]
 *  
 *          2. 계단을 오를 수 있는 경우 SCORE[N] = SCORE[N - 3] + STAIR[N - 1] + STAIR[N]
 * 
 *          3. 계단 수가 1,2,3개일 경우 예외 처리 
 *          
*/

public class P2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] Stairs = new int[N + 1];
        int[] Scores = new int[N + 1];

        for (int i = 1; i <= N; i++)
        {
            Stairs[i] = Integer.parseInt(br.readLine());
        }

        switch(N)
        {
            case 1:
                System.out.println(Stairs[1]);
                break;

            case 2:
                System.out.println(Stairs[1] + Stairs[2]);
                break;
            
            case 3:
                System.out.println(Math.max(Stairs[1] + Stairs[3], Stairs[2] + Stairs[3]));
                break;
            
            default:
                Scores[0] = 0;
                Scores[1] = Stairs[1];
                Scores[2] = Stairs[1] + Stairs[2];
                Scores[3] = Math.max(Stairs[1] + Stairs[3], Stairs[2] + Stairs[3]);

                for (int i = 4; i <= N; i++)
                {
                    Scores[i] = Math.max(Scores[i - 2] + Stairs[i], Scores[i - 3] + Stairs[i - 1] + Stairs[i]);
                }

                System.out.println(Scores[N]);
                break;

        }

        return;
    }
}