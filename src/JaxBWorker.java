import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JaxBWorker {

    public static void main(String[] args) throws JAXBException, IOException {
        String fileName = "src/file/file.xml";
        Person person = new Person("Ivanov Ivan Ivanovich", "04-12-2020");
        Person person1 = new Person("Sidorov Viktor Petrovich", "12-04-2020");
        /*List<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(person1);*/

        convertObjectToXml(person1, fileName);

        Person unmarshPerson = fromXmlToObject(fileName);
        if (unmarshPerson != null) {
            System.out.println(unmarshPerson.toString());
        }
    }

    private static Person fromXmlToObject(String filePath) {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (Person) un.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void convertObjectToXml(Person person, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(person, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
