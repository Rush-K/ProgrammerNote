# 객체 지향의 정석 JAVA 요약
> 인생을 함께 한 만큼 잊지 말자

## 1. 자주 사용하는 라이브러리
    System.IO
    util.Scanner

## 2. 자료 구조
### A. Array

### B. Stack

### C. Queue
1. Basic Queue
    import util.LinkedList;
    import util.Queue;
    ...
    Queue<T> q = new LinkedList<T>();

2. Priority Queue
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

### E. Deque
