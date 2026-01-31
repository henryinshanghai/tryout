package com.henry.tryout.leetcodes.Huawei.phase3.stack.reverse_polish_notation_150.exe;

import java.util.Stack;

public class Solution_qianwen_stack {

    public int evalRPN(String[] tokens) {
        Stack<Integer> operandStack = new Stack<>();

        // 遍历 逆波兰表达式中的每个字符...
        for (String currentTokenChar : tokens) {
            /* 根据当前字符的类型，做对应的处理 */
            switch (currentTokenChar) {
                // 如果 当前操作对象为 +，说明 需要获取结果，则：
                case "+":
                    // 把 栈顶元素 与 次栈顶元素 相加，并 把结果 入栈
                    operandStack.push(operandStack.pop() + operandStack.pop());
                    break;
                case "-": // 如果 是 -，则：
                    int num1 = operandStack.pop(); // 减数
                    int num2 = operandStack.pop(); // 被减数
                    operandStack.push(num2 - num1); // 注意顺序！
                    break;
                case "*":
                    operandStack.push(operandStack.pop() * operandStack.pop());
                    break;
                case "/":
                    num1 = operandStack.pop(); // 除数
                    num2 = operandStack.pop(); // 被除数
                    operandStack.push(num2 / num1); // Java 的 / 是‘向零取整’，符合要求
                    break;
                default: // 如果 没有匹配 任何case，说明 字符 是一个 数字字符，
                    // 则：把它转化为整数，然后入栈
                    operandStack.push(Integer.parseInt(currentTokenChar));
            }
        } /* 循环结束后，栈中 只会剩下 一个元素 也就是 表达式的计算结果 */

        // 把 栈中唯一的元素 弹出 并 返回
        return operandStack.pop();
    }
}
