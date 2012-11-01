/**
 * From object to XML via JAXB, and the other way round
 *
 * marshalling: http://bitingcode.blogspot.com/2012/10/from-object-to-xml.html
 * unmarshalling: http://bitingcode.blogspot.com/2012/11/from-xml-to-object.html
 */
package jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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

    public User xml2Obj(String xml) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(User.class);
            Unmarshaller um = ctx.createUnmarshaller();

            StringReader sr = new StringReader(xml);
            User user = (User) um.unmarshal(sr);
            return user;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        Main main = new Main();

        User user = new User();
        user.setName("Bill");
        user.setId(42);
        user.setRating(4.2F);
        System.out.println("Original: " + user);

        String xml = main.obj2xml(user);
        System.out.println("Marshalled: " + xml);
        
        User u2 = main.xml2Obj(xml);
        if(u2 != null)
          System.out.println("Unmarshalled: " + u2);
    }
}
