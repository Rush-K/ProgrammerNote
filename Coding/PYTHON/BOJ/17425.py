# 백준 온라인 저지 17425번 : 약수의 합
#
# 1. g(x)
#    - 단순하게, 약수를 전부 구하여 합을 계산 
#
# 2. effective_g(x)
#    - 한번에 x보다 작은 수들의 약수 합을 계산
#
# 3. SieveOfEratosthenes_g()
#    - 에라토스테네스의 체 알고리즘
#    - 미리 정해진 범위의 수들의 약수의 합을 계산해 둠

import sys

def getDivisor(A:int):
    result = []
    
    for i in range(1, A+1):
        if A % i == 0:
            result.append(i)

    return result

def f(A):
    result = 0
    divisors = getDivisor(A)
    for i in divisors:
        result += i

    return result

def g(x):
    result = 0
    for i in range(1, x+1):
        result += f(i)

    return result

def effective_g(x):
    result = 0
    for i in range(1, x+1):
        result += int(x / i) * i

    return result
   
def SieveOfEratosthenes_g():
    MAX = 1000000
    sum_of_divisors = [0] * (MAX + 1)
    g = [0] * (MAX + 1)

    for i in range (1, MAX+1):
        for j in range (i, MAX+1, i):
            sum_of_divisors[j] += i

    for i in range (1, MAX+1):
        g[i] = g[i - 1] + sum_of_divisors[i]

    return g

answers = SieveOfEratosthenes_g()

num_of_input = int(sys.stdin.readline())
input_list = [int(sys.stdin.readline()) for _ in range (0, num_of_input)]

results = []
for n in input_list:
    results.append(answers[n])

sys.stdout.write("\n".join(map(str, results)) + "\n")