package com.henry.tryout.idea_tips;

import com.google.common.base.Objects;

public class InvokeSuggestions {

    // 这里的方法签名中多出了一个没用的参数 - 使用 ALt + Enter，IDEA会提示你该怎么做
    public void isGoodLooking(String name) {
        if (Objects.equal(name, "Henry")) {
            System.out.println("he is good looking");
        }
    }
}
