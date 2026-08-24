# 임베디드 SW 프로그래밍을 위한 C++ 요약
> 모든 ECU 개발 고수를 향하여

## 1. 자주 사용하는 라이브러리
    stdio.h : STD IN/OUTPUT : scanf, printf..
    math.h : 각종 수학 계산 함수들의 집합
    vector
    iostream
    
## 2. 자료형
    byte
    char
    int 
    long
    long long
    unsigned int
    unsigned long
    unsigned long long

## 3. 자료 구조
### A. Array
    정적 배열 : int arr[4]; (Init : int arr[4] = {};)
    동적 배열 : int* arr = new int[4]; (Init : int* arr = new int[4]();)
    std::array : std::array<type, N> arr;
    vector<type> 을 거의 많이 씀
### B. Stack
    
### C. Queue

### D. Linked List

### E. Deque

## 4. 조건문

## 5. 반복문

## 6. 자주 사용하는 알고리즘
### A. 2차원 부분합
    사각형 (M X N) 배열의 합을 미리 구해놓는 방법
    A[x,y] = A[x-1][y] + A[x][y-1] + Av[x,y] - A[x-1][y-1]