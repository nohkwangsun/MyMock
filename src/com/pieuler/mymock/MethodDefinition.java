package com.pieuler.mymock;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class MethodDefinition {
    private Object object;
    private Method method;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] parameterTypes;
    private Class<?> returnType;

    public MethodDefinition(Object object, Method method, Object[] parameters) {
        this.object = object;
        this.method = method;
        this.methodName = method.getName();
        this.parameterTypes = method.getParameterTypes();
        this.returnType = method.getReturnType();
        this.parameters = parameters;
    }

    public Object getObject() {
        return this.object;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodDefinition that = (MethodDefinition) o;
        return Objects.equals(object, that.object) && Objects.equals(methodName, that.methodName) && Arrays.equals(parameters, that.parameters) && Arrays.equals(parameterTypes, that.parameterTypes) && Objects.equals(returnType, that.returnType);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(methodName, returnType);
        result = 31 * result + Arrays.hashCode(parameters);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }

    public static MethodDefinition of(Object object, Method method, Object[] parameters) {
        return new MethodDefinition(object, method, parameters);
    }
}
