package com.geek.dz3.repositories;

import com.geek.dz3.entities.IComparable;

public class TaskRepository implements ITaskRepository {
    private IComparable[] items;
    private int itemCount = 0;

    @Override
    public boolean isAllowedAddNewitem() {
        return itemCount < this.items.length;
    }

    @Override
    public boolean additem(IComparable item) {
        if (isAllowedAddNewitem()) {
            for (int i = 0; i < this.items.length; i++) {
                if (this.items[i] == null) {
                    this.items[i] = item;
                    this.itemCount++;
                    return true;
                }
            }
        }
        return false;
    }

    public TaskRepository(int itemCount) {
        this.items = new IComparable[itemCount];
    }

    @Override
    public IComparable[] getItems() {
        return this.items;
    }

    private Integer[] finditems(Object obj) {
        Integer[] founditems = new Integer[this.items.length];
        int founditemCount = 0;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null && this.items[i].compareTo(obj)) {
                founditems[founditemCount] = i;
            }
            founditemCount++;
        }
        return founditems;
    }

    @Override
    public void deleteItem(IComparable obj) {
        Integer[] founditems = finditems(obj);
        for (Integer founditem : founditems) {
            if (founditem != null) {
                this.items[founditem] = null;
                this.itemCount--;
            }
        }
    }

    @Override
    public IComparable[] findItems(IComparable obj) {
        int findResultsCount = 0;
        Integer[] foundItems = finditems(obj);
        IComparable[] findResults = new IComparable[foundItems.length];
        for (Integer foundItem : foundItems) {
            if (foundItem != null) {
                findResults[findResultsCount] = this.items[foundItem];
                findResultsCount++;
            }
        }
        return findResults;
    }
}


