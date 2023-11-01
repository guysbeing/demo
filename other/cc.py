def knapsack(weights, values, capacity):
    n = len(weights)  # 物品数量
    dp = [[0] * (capacity + 1) for _ in range(n + 1)]  # 创建dp数组，大小为(n+1) x (capacity+1)

    for i in range(1, n + 1):
        for j in range(1, capacity + 1):
            if weights[i - 1] <= j:
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1])
            else:
                dp[i][j] = dp[i - 1][j]

    return dp[n][capacity]  # 返回最终结果

# 示例用法
weights = [2, 3, 4, 5]  # 物品的重量
values = [3, 4, 5, 6]  # 物品的价值
capacity = 8  # 背包的容量

result = knapsack(weights, values, capacity)
print("背包中物品的最大总价值为:", result)