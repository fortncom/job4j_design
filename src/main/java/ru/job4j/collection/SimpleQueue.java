package ru.job4j.collection;

public class SimpleQueue<T> {
    //добавил методы в другие классы скопировать оба
    private final  SimpleStack<T> in = new SimpleStack<>();
    private final  SimpleStack<T> out = new SimpleStack<>();

    //получить первое значение и удалить его из списка
    public T poll() {
        if (out.size() == 0) {
            while (in.size() != 0) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    // помещает значение в конец
    public void push(T value) {
        in.push(value);
    }
}
