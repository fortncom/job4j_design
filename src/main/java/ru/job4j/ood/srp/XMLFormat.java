package ru.job4j.ood.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@XmlRootElement(name =  "employees")
public class XMLFormat<T extends Employee> implements Format<T> {

    @XmlElement(name = "employee")
   private List<T> list;

    @Override
    public String reform(List<T> list) {
        this.list = list;
        String rsl = "";
        try {
            JAXBContext context = JAXBContext.newInstance(this.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                    marshaller.marshal(this, writer);
                    rsl = writer.getBuffer().toString();
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
