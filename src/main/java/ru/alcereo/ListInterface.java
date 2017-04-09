package ru.alcereo;

import java.util.List;

@FunctionalInterface
public interface ListInterface<TYPE> {
    List<TYPE> getList();
}
