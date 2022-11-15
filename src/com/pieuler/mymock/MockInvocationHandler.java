package com.pieuler.mymock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MockInvocationHandler implements InvocationHandler {
    private Map<MethodDefinition, Object> map = new HashMap<>();
    private static final Object DEFAULT_VALUE = null;

    public void updateMethodReturnValue(MethodDefinition method, Object returnValue) {
        map.put(method, returnValue);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodDefinition methodDefinition = MethodDefinition.of(proxy, method, args);
        InvocationProgress.update(methodDefinition);
        return map.getOrDefault(methodDefinition, DEFAULT_VALUE);
    }
}