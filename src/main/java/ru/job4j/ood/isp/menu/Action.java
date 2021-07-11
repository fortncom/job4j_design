package ru.job4j.ood.isp.menu;

public interface Action {

    boolean execute(Input in, Item item);
}


class DemoAction implements Action {

    private final Output out;

    public DemoAction(Output out) {
        this.out = out;
    }

    @Override
    public boolean execute(Input in, Item item) {
        out.println("=== Triggered Demo Action ====");
        String text = item.getText();
        if (text != null) {
            out.println(text);
        }
        return true;
    }
}
