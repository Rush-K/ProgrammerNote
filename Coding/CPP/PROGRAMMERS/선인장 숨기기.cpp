// Site: Programmers
// Problem: 선인장 숨기기
// Tags: 이분 탐색
// Difficulty: Level 2

#include <string>
#include <iostream>
#include <vector>

using namespace std;

void calculate(vector<vector<int>> map, vector<vector<int>> &sumMap, int m, int n)
{ // 누적합을 미리 계산
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (i == 0) sumMap[i + 1][j + 1] = sumMap[i + 1][j] + map[i][j];
            else if (j == 0) sumMap[i + 1][j + 1] = sumMap[i][j + 1] + map[i][j];
            else sumMap[i + 1][j + 1] = sumMap[i][j + 1] + sumMap[i + 1][j] - sumMap[i][j] + map[i][j];
        }
    }
}

bool isMatch(vector<vector<int>> sumMap, int m, int n, int h, int w, vector<int>& answer)
{ // 누적합 배열로부터 비가 오지 않은 영역을 찾아 반환
    bool result = true;
    
    for (int i = 1; i < m + 1 - h + 1; i++)
    {
        for (int j = 1; j < n + 1 - w + 1; j++)
        {
            if (sumMap[i + h - 1][j + w - 1] - sumMap[i + h - 1][j - 1] - sumMap[i - 1][j + w - 1] + sumMap[i - 1][j - 1] == 0)
            {
                answer.clear();
                answer.push_back(i - 1);
                answer.push_back(j - 1);
                
                return false;
            }
        }
    }
    
    return true;
}

vector<int> solution(int m, int n, int h, int w, vector<vector<int>> drops) {
    vector<int> answer = {0, 0};
    vector<vector<int>> map(m, vector<int>(n));
    vector<vector<int>> sumMap(m + 1, vector<int>(n + 1));
    int low = 0;
    int high = drops.size() - 1;
    int mid = (low + high) / 2;
    
    while (low <= high)
    {
        int defMid = mid;
        cout << low << " " << mid << " " << high << endl;
        
        for (int i = 0; i <= defMid; i++) map[drops[i][0]][drops[i][1]] = 1;
        calculate(map, sumMap, m, n);
        
        if (!isMatch(sumMap, m, n, h, w, answer)) // no rain
        {
            low = mid + 1;
            mid = (high + low) / 2;
        }
        else
        {
            high = mid - 1;
            mid = (high + low) / 2;
        }
        
        for (int i = 0; i <= defMid; i++) map[drops[i][0]][drops[i][1]] = 0;
    }
    
    return answer;
}
