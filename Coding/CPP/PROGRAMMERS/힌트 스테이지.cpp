// Site: Programmers
// Problem: 선인장 숨기기
// Tags: DFS 백트래킹
// Difficulty: Level 2

#include <string>
#include <vector>
#include <iostream>
#include <limits>
#include <algorithm>

using namespace std;

void dfs(const vector<vector<int>>& cost,const vector<vector<int>>& hint, vector<bool>& hintVisited, vector<int>& hintDefault, int root, long& answer, long& resultCost)
{   
    for (int i = root; i < hintVisited.size(); i++)
    {
        if (!hintVisited[i]) 
        {
            hintVisited[i] = true;
            resultCost += hint[i][0];
            
            for (int j = 1; j < hint[i].size(); j++)
            {
                resultCost -= cost[hint[i][j] - 1][hintDefault[hint[i][j]] >= cost[0].size() ? cost[0].size() - 1 : hintDefault[hint[i][j]]];
                hintDefault[hint[i][j]]++;
                resultCost += cost[hint[i][j] - 1][hintDefault[hint[i][j]] >= cost[0].size() ? cost[0].size() - 1 : hintDefault[hint[i][j]]];
            }
            
            answer = min(answer, resultCost);
            
            dfs(cost, hint, hintVisited, hintDefault, i + 1, answer, resultCost);
            
            hintVisited[i] = false;
            resultCost -= hint[i][0];
            
            for (int j = 1; j < hint[i].size(); j++)
            {
                resultCost -= cost[hint[i][j] - 1][hintDefault[hint[i][j]] >= cost[0].size() ? cost[0].size() - 1 : hintDefault[hint[i][j]]];
                hintDefault[hint[i][j]]--;
                resultCost += cost[hint[i][j] - 1][hintDefault[hint[i][j]] >= cost[0].size() ? cost[0].size() - 1 : hintDefault[hint[i][j]]];
            }
        }
    }
}

int solution(vector<vector<int>> cost, vector<vector<int>> hint) {
    long answer = 0;
    long resultCost = 0;
    
    vector<bool> hintVisited(hint.size(), false);
    vector<int> hintDefault(cost.size() + 1, 0);
    
    for (int i = 0; i < hint.size(); i++) // 힌트 값이 0일때는 Default 처리
    {
        if (hint[i][0] == 0)
        {
            hintVisited[i] = true;
            for (int j = 1; j < hint[i].size(); j++) hintDefault[hint[i][j]]++;
        }
    }
    
    for (int i = 1; i < hintDefault.size(); i++) 
    {
        answer += cost[i - 1][hintDefault[i] >= cost[i - 1].size() ? cost[i - 1].size() - 1 : hintDefault[i]];
    }
    
    resultCost = answer;
    dfs(cost, hint, hintVisited, hintDefault, 0, answer, resultCost);
    
    return answer;
}