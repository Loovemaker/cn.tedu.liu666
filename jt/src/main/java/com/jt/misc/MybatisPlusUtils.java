package com.jt.misc;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.util.function.Consumer;

public class MybatisPlusUtils {

    public static <T> void wrapperExecution(final Wrapper<T> wrapper, Consumer<Wrapper<T>> consumer) {
        synchronized (wrapper) {
            wrapper.clear();
            consumer.accept(wrapper);
            wrapper.clear();
        }
    }
}
