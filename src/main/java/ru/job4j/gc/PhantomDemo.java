package ru.job4j.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

public class PhantomDemo extends PhantomReference<String> {

        private String name;

        public PhantomDemo(String referent,
                           ReferenceQueue<? super String> q,
                           String name) {
            super(referent, q);
            this.name = name;
        }

        @Override
        public String get() {
            return name;
        }

    private static class PhantomStorage {

        private ReferenceQueue<String> queue = new ReferenceQueue<>();

        private List<PhantomDemo> phantoms = new LinkedList<>();

        public void add(String someData) {
            PhantomDemo phantom = new PhantomDemo(someData, queue, "my ref");
            phantoms.add(phantom);
        }

        public void utilizeResource() {
            for (ListIterator<PhantomDemo> i = phantoms.listIterator(); i.hasNext();) {
                PhantomDemo current = i.next();
                if (current != null && current.isEnqueued()) {
                    System.out.println("Utilized " + current.get());
                    current.clear();
                    i.remove();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PhantomStorage storage = new PhantomStorage();
        String data = "123".repeat(1000);
        storage.add(data);
        data = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        storage.utilizeResource();
    }
}
