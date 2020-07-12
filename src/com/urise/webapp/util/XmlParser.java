package com.urise.webapp.util;

import com.urise.webapp.model.Resume;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.OutputStream;

public class XmlParser {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    @SuppressWarnings("rawtypes")
    public XmlParser(Class... boundedClasses) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(boundedClasses);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException jaxbe) {
            throw new IllegalArgumentException(jaxbe);
        }
    }

    public void marshal(Object obj, OutputStream outputStream) {
        try {
            marshaller.marshal(obj, outputStream);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Resume unmarshal(InputStream inputStream) { // <T> T <-> Resume
        try {
            return (Resume) unmarshaller.unmarshal(inputStream); // (T) <-> Resume
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
