package com.dev.store.mapper;

public interface Mapper <F, T>{

    T map(F object);
}
