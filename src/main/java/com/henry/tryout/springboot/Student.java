package com.henry.tryout.springboot;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// 手段2：使用 Spring提供的校验注解 来 校验UI传入的参数值 - {@NotNull, @NotEmpty, @Min, @Max, @Length}
public class Student {
//    public static final long serialVersionUID = 100L;

    @NotNull(message = "传入的姓名为null，请传入有效值") // 这里的message是 当校验不通过时，返回给UI的error message
    @NotEmpty(message = "传入的姓名为空字符串，请传入有效值")
    @JsonProperty(value = "name")
    private String name; // 姓名

    @NotNull(message = "传入的分数为null，请传入有效值")
    @Min(value = 0, message = "传入的分数有误，分数应该在0~100的区间中")
    @Min(value = 100, message = "传入的分数有误，分数应该在0~100的区间中")
    @JsonProperty(value = "score")
    private Integer score; // 分数（满分100分）

    @NotNull(message = "传入的电话为null，请传入有效值")
    @NotEmpty(message = "传入的电话为空字符串，请传入有效值")
    @Length(min=11, max = 11, message = "传入的电话号码长度有误，长度必须为11位")
    @JsonProperty(value = "mobile")
    private String mobile; // 电话号码（11位）
}
