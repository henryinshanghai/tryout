package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.chain_of_responsibility_04;

public abstract class ProcessingObject_01<T> {

    // 包含 当前对象的后继
    protected ProcessingObject_01<T> successor;

    public void setSuccessor(ProcessingObject_01<T> successor) {
        this.successor = successor;
    }

    public T handle(T input) {
        T output = handleWork(input);
        if (successor != null) {
            return successor.handle(output);
        }

        return output;
    }

    abstract protected T handleWork(T input);
}
