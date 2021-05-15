package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlAttribute
    private boolean married;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    @XmlElementWrapper
    @XmlElement(name = "child")
    private String[] children;
    private Location location;

    public User() {
    }

    public User(boolean married, String name, int age, String[] children, Location location) {
        this.married = married;
        this.name = name;
        this.age = age;
        this.children = children;
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{"
                + "married=" + married
                + ", name='" + name + '\''
                + ", age=" + age
                + ", children=" + Arrays.toString(children)
                + ", location=" + location
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return married == user.married
                && age == user.age
                && Objects.equals(name, user.name)
                && Arrays.equals(children, user.children)
                && Objects.equals(location, user.location);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(married, name, age, location);
        result = 31 * result + Arrays.hashCode(children);
        return result;
    }

    public static void main(String[] args) throws JAXBException {
        Location location = new Location(50.0, 50.0);
        final User user = new User(true, "Pol", 28,
                new String[]{"Tom", "Wilson"}, location);

        JAXBContext context = JAXBContext.newInstance(User.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String result = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(user, writer);
            result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(result)) {
            User userCopy = (User) unmarshaller.unmarshal(reader);
            System.out.println(user.equals(userCopy));
        }
    }
}
