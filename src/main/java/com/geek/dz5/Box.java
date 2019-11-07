package com.geek.dz5;

import java.util.ArrayList;
import java.util.Iterator;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits = new ArrayList<T>();

    public boolean addFruit(T fruit) {
        return this.fruits.add(fruit);
    }

    public float getWeight() {
        float sum = 0f;
        Iterator<T> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            sum += iterator.next().getWeight();
        }
        return sum;
    }

    public int getCount() {
        return fruits.size();
    }

    public boolean compare(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Box)) {
            return false;
        }
        Box otherBox = (Box) o;
        return this.getWeight() - otherBox.getWeight() < 0.001f;
    }

    public void pourToOtherBox(Box<T> otherBox) {
        Iterator<T> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            otherBox.addFruit(iterator.next());
        }
        fruits.clear();
    }

    @Override
    public String toString() {
        return String.format("Count of fruits:%d, weight: %f", getCount(), getWeight());
    }
}
