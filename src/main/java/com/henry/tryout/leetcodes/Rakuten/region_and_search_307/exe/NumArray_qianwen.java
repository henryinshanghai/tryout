package com.henry.tryout.leetcodes.Rakuten.region_and_search_307.exe;

public class NumArray_qianwen {
    // 线段树（用数组实现，下标从1开始）
    // 当前位置 -> 当前位置的节点 所表示的区间中 所有元素的和
    private int[] currentSpotToItsSum;

    private int[] numArr;
    private int numAmount;

    /**
     * 构造方法
     * 作用：① 给 调用者 提供 创建实例的手段；
     * ② 完成对成员变量的 初始化
     * 🐖 “线段树，四倍开，安全省心不用猜。”
     */
    public NumArray_qianwen(int[] numArr) {
        /* 成员变量初始化 */
        this.numArr = numArr;
        this.numAmount = numArr.length;
        this.currentSpotToItsSum = new int[4 * numAmount]; // 安全起见，分配 4n 空间

        // 构建 线段树
        build(1, 0, numAmount - 1);
    }

    /**
     * 以 tree[treeIndex] 为根节点，构建一棵子线段树，
     * 用于维护 原数组 nums 中 区间 [start, end] 的聚合信息（如和），并 将结果存入 tree[treeIndex]
     * 🐖 区间从1开始
     * 最终效果：整棵线段树 被填满，每个节点都 正确保存了 其对应区间的聚合值（如和）
     *
     * @param currentNodeSpot 当前线段树 根节点的 层序遍历节点编号
     * @param itsLeftBar      区间的左边界  对应 原始数组的左边界(包含)
     * @param itsRightBar     区间的右边界  对应 原始数组的右边界(包含)
     */
    private void build(int currentNodeSpot,
                       int itsLeftBar,
                       int itsRightBar) {
        // 如果 当前节点所表示的区间的 左边界 和 右边界 相等，说明 它是一个 单一元素区间，也就是 线段树中的一个叶子节点，
        if (itsLeftBar == itsRightBar) {
            // 则：该位置上的节点的sum值 就是 原始数组中的元素值（因为不存在任何子节点）
            currentSpotToItsSum[currentNodeSpot] = numArr[itsLeftBar];
        } else { // 否则：说明 当前节点是一个内部节点，则👇
            // 计算 区间的中间位置 用于把原始区间 对半分裂
            int middleSpot = itsLeftBar + (itsRightBar - itsLeftBar) / 2;
            // ① 构建 当前节点 的左子树（对应于 分割出的左半区间 ）
            build(currentNodeSpot * 2, itsLeftBar, middleSpot);
            // ② 构建 当前节点 的右子树（对应于 分割出的右半区间）
            build(currentNodeSpot * 2 + 1, middleSpot + 1, itsRightBar);

            // ③ 子树构建完成后，计算 当前树节点的sum值 = 其左子节点的sum值 + 其右子节点的sum值
            currentSpotToItsSum[currentNodeSpot] =
                    currentSpotToItsSum[currentNodeSpot * 2] + currentSpotToItsSum[currentNodeSpot * 2 + 1];
        }
    }

    /**
     * 把 原始数组中，指定位置上的元素 更新为 指定的新值
     * 🐖 需要 同步更新 线段树中 所有受影响的节点 以 保持区间聚合信息(sum)的正确性
     * @param givenArrSpot 指定位置
     * @param newValue   指定的新值
     */
    public void update(int givenArrSpot, int newValue) {
        update(1, 0, numAmount - 1, givenArrSpot, newValue);
        numArr[givenArrSpot] = newValue; // 可选：同步原数组（便于调试或后续使用）
    }

    private void update(int currentNodeSpot,
                        int itsLeftBar,
                        int itsRightBar,
                        int givenArrSpot,
                        int newValue) {
        // 如果 当前节点所表示的区间的左边界 等于 其右边界，说明 当前节点 是 线段树的叶子节点，则：
        if (itsLeftBar == itsRightBar) {
            // 直接 使用 调用者传入的newValue 更新 它的sum值
            currentSpotToItsSum[currentNodeSpot] = newValue;
        } else { // 如果 区间不是 单个元素区间，说明 当前节点 是 内部节点，则：👇
            int middle = itsLeftBar + (itsRightBar - itsLeftBar) / 2;

            // 如果 调用者想要更新的元素位置 属于 左半区间，说明 需要 在当前节点的左子树中 继续查找，则：
            if (givenArrSpot <= middle) {
                // ① 递归地 在左子树中 查找 并 尝试更新
                update(currentNodeSpot * 2,
                        itsLeftBar,
                        middle,
                        givenArrSpot,
                        newValue);
            } else {
                // ② 递归地 在右子树中 查找 并 尝试更新
                update(currentNodeSpot * 2 + 1,
                        middle + 1,
                        itsRightBar,
                        givenArrSpot,
                        newValue);
            }

            // ③ 在左子树/右子树中 完成更新后，维护 当前节点的sum值
            currentSpotToItsSum[currentNodeSpot] =
                    currentSpotToItsSum[currentNodeSpot * 2] +
                            currentSpotToItsSum[currentNodeSpot * 2 + 1]; // 回溯(递归结束后) 更新父节点
        }
    }


    /**
     * 获取到 原始数组 指定闭区间中 所有元素的和
     *
     * @param leftBar      指定闭区间的左边界
     * @param rightBar     指定闭区间的右边界
     * @return  闭区间中 所有元素的加和结果
     */
    public int sumRange(int leftBar, int rightBar) {
        return query(1, 0, numAmount - 1, leftBar, rightBar);
    }

    /**
     * 从 线段树的 treeIndex 节点（它管 [start, end]）出发，计算 原数组 在 用户指定区间 [l, r] 内的聚合值（如和）
     * 在线段树中查询区间 [l, r] 的和
     * @param currentNodeSpot   当前节点 在线段树中的 层序遍历 节点次序
     * @param nodesLeftBar      当前节点 所表示的区间的 左边界
     * @param nodesRightBar     当前节点 所表示的区间的 右边界
     * @param rangeLeftBar      指定 位置查询区间的 左边界
     * @param rangeRightBar     指定 位置查询区间的 右边界
     * @return 指定位置区间中 所有元素的加和结果
     */
    private int query(int currentNodeSpot,
                      int nodesLeftBar,
                      int nodesRightBar,
                      int rangeLeftBar,
                      int rangeRightBar) {
        // 情形1：如果 当前节点所表示的位置区间 与 指定的查询区间 不存在 任何交集
        if (rangeRightBar < nodesLeftBar || nodesRightBar < rangeLeftBar) {
            // 说明 区间中 不存在 任何元素，则：把 0 返回给 上一级调用
            return 0;
        }

        // 情形2：如果 当前节点所表示的位置区间 被 查询区间 完全覆盖，说明 其sum值需要被累加，
        if (rangeLeftBar <= nodesLeftBar && nodesRightBar <= rangeRightBar) {
            // 把 节点的sum值 返回给上一级调用
            return currentSpotToItsSum[currentNodeSpot];
        }

        /* 情形3：如果 当前节点所表示的位置区间 与 查询区间 部分重叠，说明 不能直接返回其sum值(因为sum包含有 查询区间外的元素)，而需要 递归查询左右子树 ，则👇 */
        int middleSpot = nodesLeftBar + (nodesRightBar - nodesLeftBar) / 2;
        // ① 从左子树中 查询并累加 查询范围内的sum值
        int leftSumContribution = query(currentNodeSpot * 2,
                nodesLeftBar,
                middleSpot,
                rangeLeftBar,
                rangeRightBar);

        // ② 从右子树中 查询并累加 查询范围内的sum值
        int rightSumContribution = query(currentNodeSpot * 2 + 1,
                middleSpot + 1,
                nodesRightBar,
                rangeLeftBar,
                rangeRightBar);

        // ③ 把 左右子树中 查找到的sum值 合并起来，返回给上一级调用
        return leftSumContribution + rightSumContribution;
    }
}
