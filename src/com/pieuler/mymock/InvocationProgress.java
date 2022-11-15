package com.pieuler.mymock;

public class InvocationProgress {
    private static MethodDefinition methodDefinition;

    public static void update(MethodDefinition methodDefinition) {
        InvocationProgress.methodDefinition = methodDefinition;
    }

    public static MethodDefinition pull() {
        MethodDefinition out = InvocationProgress.methodDefinition;
        InvocationProgress.methodDefinition = null;
        return out;
    }
}
