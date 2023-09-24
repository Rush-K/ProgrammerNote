//package Coding.JAVA.Source.Design;

import java.util.Scanner;
import java.lang.String;

/* 
 * 문제 출처) 백준온라인저지 1790번 수 이어 쓰기2
 * 
 * 핵심 요약) 이진 탐색
 * 
 * 핵심 설명) 1. 원하는 자릿수에 도달할 때 까지 이진 탐색 진행 (index > k | index = k | index < k)
 *  
 *          2. toString()은 String 객체를 반환하기 때문에 메모리 소요가 많음
 *          
*/

public class P1790 {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = sc.nextInt();

        int index = 0;

        for (int i = 1; i <= N; i++)
        {
            int iLength = 1;
            int iCopy = i;

            while (iCopy / 10 != 0) 
            {
                iCopy = iCopy / 10;
                iLength++;
            }

            index += iLength;

            if (k > index) 
            {
                if (i == N) System.out.println("-1");
                continue;
            }
            else if (k == index) 
            {
                String iString = Integer.toString(i);
                System.out.println(iString.substring(iString.length() - 1));
                break;
            }
            else
            {
                String iString = Integer.toString(i);
                System.out.println(iString.substring(iString.length() - 1 - (index - k), iString.length() - (index - k)));
                break;
            }
        }
    }
}
