/**
 * From object to XML via JAXB
 * info and comments: http://bitingcode.blogspot.com/2012/10/from-object-to-xml.html
 */
package jaxb;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {

    public String obj2xml(User user) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(User.class);
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            marshaller.marshal(user, sw);

            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return "<bad />";
        }
    }

    public static void main(String[] args) {
        Main main = new Main();

        User user = new User();
        user.setName("Bill");
        user.setId(42);
        user.setRating(4.2F);
        System.out.println(user);

        String xml = main.obj2xml(user);
        System.out.println(xml);
    }
}
