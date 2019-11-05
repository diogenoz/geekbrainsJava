package com.geek.dz3.repositories;


import com.geek.dz3.entities.IFindable;

public interface ITaskRepository {
    boolean isAllowedAddNewItem();

    boolean addItem(IFindable item);

    IFindable[] getItems();

    void deleteItem(IFindable obj);

    IFindable[] findItems(IFindable obj);
}

