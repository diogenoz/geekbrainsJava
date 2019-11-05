package com.geek.dz3.repositories;

import com.geek.dz3.entities.IFindable;

public class TaskRepository implements ITaskRepository {
    private IFindable[] items;
    private int itemCount = 0;

    @Override
    public boolean isAllowedAddNewItem() {
        return itemCount < this.items.length;
    }

    @Override
    public boolean addItem(IFindable item) {
        if (isAllowedAddNewItem()) {
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
        this.items = new IFindable[itemCount];
    }

    @Override
    public IFindable[] getItems() {
        return this.items;
    }

    private Integer[] finditems(Object obj) {
        Integer[] founditems = new Integer[this.items.length];
        int founditemCount = 0;
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null && this.items[i].findByPatternObj(obj)) {
                founditems[founditemCount] = i;
            }
            founditemCount++;
        }
        return founditems;
    }

    @Override
    public void deleteItem(IFindable obj) {
        Integer[] founditems = finditems(obj);
        for (Integer founditem : founditems) {
            if (founditem != null) {
                this.items[founditem] = null;
                this.itemCount--;
            }
        }
    }

    @Override
    public IFindable[] findItems(IFindable obj) {
        int findResultsCount = 0;
        Integer[] foundItems = finditems(obj);
        IFindable[] findResults = new IFindable[foundItems.length];
        for (Integer foundItem : foundItems) {
            if (foundItem != null) {
                findResults[findResultsCount] = this.items[foundItem];
                findResultsCount++;
            }
        }
        return findResults;
    }
}


