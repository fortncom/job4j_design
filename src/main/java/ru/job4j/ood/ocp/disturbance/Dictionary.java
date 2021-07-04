package ru.job4j.ood.ocp.disturbance;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Pupil нарушает принцип OCP тем, что содержит поле
 * ImplDictionary которое не даёт возможности к расширению,
 * так как ссылочный тип, имеющий возможности к расширению
 * не является абстракцией, да и сам класс Pupil не зависит
 * от абстракции, что будет мешать его использованию
 * другими классами.
 *
 */

public interface Dictionary {

    void setNote(String s);

    public static class ImplDictionary implements Dictionary {

        private List<String> notes = new ArrayList<>();

        @Override
        public void setNote(String note) {
            notes.add(note);
        }
    }

    public static class Pupil {

        private String name;
        private ImplDictionary dictionary;

        public Pupil(String name, ImplDictionary dictionary) {
            this.name = name;
            this.dictionary = dictionary;
        }

        public ImplDictionary getDictionary() {
            return dictionary;
        }

        public String getName() {
            return name;
        }
    }

}
