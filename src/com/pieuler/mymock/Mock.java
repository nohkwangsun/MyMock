package com.pieuler.mymock;

import java.lang.reflect.Proxy;

public class Mock {

    public static <T> T mock(Class<T> clazz) {
        try {
            return clazz.isInterface() ? createProxy(clazz) : createObject(clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T createObject(Class<T> clazz) throws Exception {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new MockInvocationHandler());
    }

    private static <T> T createProxy(Class<T> clazz) throws Exception {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MockInvocationHandler());
    }

    public static <R> void stub(R realValue, R returnValue) {
        MethodDefinition methodDefinition = InvocationProgress.pull();
        if (methodDefinition == null) {
            throw new RuntimeException("Mock method should be called before stubbing.");
        }
        Object proxy = methodDefinition.getObject();
        MockInvocationHandler handler = (MockInvocationHandler) Proxy.getInvocationHandler(proxy);
        handler.updateMethodReturnValue(methodDefinition, returnValue);
    }

}
