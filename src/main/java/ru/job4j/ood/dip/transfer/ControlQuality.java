package ru.job4j.ood.dip.transfer;

import java.util.List;

public class ControlQuality {

    protected List<Store> stores;
    protected List<Food> foods;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public ControlQuality(List<Store> stores, List<Food> foods) {
        this.stores = stores;
        this.foods = foods;
    }

    public void quality(Food food) {
        for (Store st : stores) {
            if (st.accept(food)) {
                st.add(food);
            }
        }
    }

    public void sort() {
        for (int i = 0; i < foods.size(); i++) {
            quality(foods.get(i));
        }
        foods.clear();
    }

    public void resort() {
        for (Store store : stores) {
            foods.addAll(store.giveBack());
            store.clear();
        }
        sort();
    }

}
