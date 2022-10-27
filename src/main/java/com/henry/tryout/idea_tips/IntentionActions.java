package com.henry.tryout.idea_tips;

public class IntentionActions {
    public static void main(String[] args) {
        boolean isHandsome = true;

        // 添加断点后，Alt + Enter会给出断点相关的actions
        if (isHandsome) {
            System.out.println("it gotta be Henry, right?");
        }
    }
}
