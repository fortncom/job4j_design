package ru.job4j.ood.lsp.transfer;

import java.util.List;

public class ControlQuality {

    protected List<Store> store;

    public ControlQuality(List<Store> store) {
        this.store = store;
    }

    public void quality(Food food) {
        for (Store st : store) {
            if (st.accept(food)) {
                st.add(food);
            }
        }
    }

}
