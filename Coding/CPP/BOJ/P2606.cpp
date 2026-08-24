#include <stdio.h>

void dfs(int *nodes, int **map, int N, int node)
{
    for (int i = 1; i < N + 1; i++)
    {
        if (map[node][i] == 1) 
        {
            map[node][i] = 2;
            nodes[i] = 1;

            dfs(nodes, map, N, i);
        }
        else if (map[node][i] == 2) return;
    }

    return ;
}

int main()
{
    int N;
    int L;

    scanf("%d", &N);
    scanf("%d", &L);

    int MAP[N + 1][N + 1];
    int NODES[N + 1];

    int **pMAP = &MAP;

    NODES[1] = 1;

    for (int i = 0; i < L; i++)
    {
        int source, destination;

        scanf("%d %d", &source, &destination);

        MAP[source][destination] = 1;
        MAP[destination][source] = 1;
    }

    dfs(NODES, MAP, N, 1);

    return 0;
}