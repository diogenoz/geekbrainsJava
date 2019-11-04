package com.geek.dz3.repositories;


import com.geek.dz3.entities.IComparable;

public interface ITaskRepository {
    boolean isAllowedAddNewitem();

    boolean additem(IComparable item);

    IComparable[] getItems();

    void deleteItem(IComparable obj);

    IComparable[] findItems(IComparable obj);
}

