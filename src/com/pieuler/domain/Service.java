package com.pieuler.domain;

import java.util.List;

public interface Service<I, T extends Entity> {
    T get(I id);
    List<T> list();
    long create(I t);
    void update(I id, T t);
    void delete(I id);
}