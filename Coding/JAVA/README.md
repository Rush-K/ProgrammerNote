# 객체 지향의 정석 JAVA 요약
> 인생을 함께 한 만큼 잊지 말자

## 1. 자주 사용하는 라이브러리
    System.IO
    util.Scanner
    lang.Math

## 2. 자료 구조
### A. Array

### B. Stack

### C. Queue
#### [1] Basic Queue

    import util.LinkedList;
    import util.Queue;
    ...
    Queue<T> q = new LinkedList<T>();

#### [2] Priority Queue

    import util.PriorityQueue;
    ...
    PriorityQueue<T> q = new PriorityQueue<>(new Comparator<T>() {
        @Override
        public T compare(T o1, T o2)
        {
            return o1.compareTo(o2);
        } 
    })

### D. Linked List

    import util.LinkedList;
    ...
    LinkedList<T> l = new LinkedList<T>();

### E. Deque

## 3. 알고리즘 작성 
### A. 정렬
#### [1] 배열 정렬

    import java.util.Arrays;
    ...
    Arrays.sort(something);
    ...
    case 1) 내림차순 
    Arrays.sort(something, Collections.reverseOrder());
    또는, for문 이용하여 reverse 시키기

    case 2) 오름차순
    Arrays.sort(something);

#### [2] LIST(구조체) 정렬

    ...
    Collections.sort(something);
    ...

#### [3] 사용자 정의 객체 정렬

    class NUMBER implements Comparable<NUMBER>
    {
        @Override
        public int compareTo(NUMBER o1)
        {
            return Integer.compare(o1, this);
        }
        ....
    }
    ...
    List<NUMBER> NUMBERS = new List<NUMBER>();
    ...
    Collections.sort(NUMBERS);

### B. 탐색 
#### [1] 깊이 우선 탐색
재귀함수 사용

    ex)
    int dfs()
    {
        ...
        if (case exists) dfs();
        ...
    } 

#### [2] 너비 우선 탐색
자료구조(Queue 등)와 while 문 사용
    
    ex)
    while (!q.isEmpty())
    {
        T t = q.poll();
        ...
        if (case exist) q.add(new T());
        ...
    }
