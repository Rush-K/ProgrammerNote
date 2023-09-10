#include <stdio.h>
#include <math.h>

/* 
 * 문제 출처) 백준온라인저지 1629번 곱셈
 * 
 * 핵심 요약) 자료형 & 분할 정복 & 모듈러 연산
 * 
 * 핵심 설명) 1. 문제에서 주어진 자료형의 각 크기는 21억 (= C++ long Type)
 *             계산 시, long * long => long long 의 값이 도출 될 수 있으므로, 자료형에 유의해야함
 *  
 *          2. 모듈러 연산의 이해가 필요
 *             (a + b) mod q = (a mod q + b mod q) mod q
 *             (a - b) mod q = (a mod q - b mod q) mod q
 *             (a * b) mod q = (a mod q * b mod q) mod q
 *             a mod q 의 modular inverse 
 *             => a * b mod q = 1 일때, b mod q의 값 (a, b는 서로소)
 *          
 *          3. 분할 정복
*/

long long divideConquer(long long A, long long B, long long C)
{
    if (B == 0) return 1;
    if (B == 1) return A % C;
    else if (B % 2 == 0) return (divideConquer(A, B / 2, C) * divideConquer(A, B / 2, C)) % C;
    else return (divideConquer(A, 1, C) * divideConquer(A, B - 1, C)) % C;
}

int main() 
{
    long long A;
    long long B;
    long long C;

    long long result;

    scanf("%lld %lld %lld", &A, &B, &C);

    result = divideConquer(A, B, C);

    printf("%lld", result);

    return 0;
}

