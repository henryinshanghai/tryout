package com.henry.tryout.software_design.program_moves.functional_program.lazy_calculate.in_Stream_process;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.mockito.Mockito.*;

// 验证：Java8引入的流式处理的特性 - 惰性求值
// 手段：
//  #1 使用Mockito提供的verify()方法 来 断言Function类的apply()方法有没有被执行到 - 如果Stream开始了流水线处理，则：apply()方法必然会被执行
//  #2 对于Stream来说，如果不调用终端操作，则：流水线处理不会被触发
// 结果：apply()方法并没有被执行 -> 验证了 Stream流式处理时的惰性求值策略
public class StudentTest {

    @Test
    public void test() {
        Student jack = new Student("Jack", 18, Gender.MALE);
        Student rose = new Student("Rose", 18, Gender.FEMALE);

        List<Student> students = Arrays.asList(jack, rose);

        // 模拟对象 - 如果向function对象传入了参数jack,则：会得到一个预期返回值 Jack
        Function<Student, String> function = mock(Function.class);
        when(function.apply(jack)).thenReturn("Jack");

        // 映射操作 - Stream流式处理的特性：在调用终端操作前，中间操作都不会执行 aka 惰性执行
        students.stream().map(function);

        // 验证 - 这里应该会验证失败 因为30行不会执行到27行预想的调用 -> 证明了Stream的惰性求值特性
        verify(function).apply(jack);
    }
}
