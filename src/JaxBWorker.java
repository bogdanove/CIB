import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JaxBWorker {

    public static void main(String[] args) {
        String fileName = "src/file/file.xml";
        Person person = new Person("Ivanova Nina Ivanovna", "04-12-2020");
        Person person1 = new Person("Sidorov Viktor Petrovich", "12-04-2020");
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(person1);

        Persons pers = new Persons();
        //
        pers.setPersons(persons);

        convertObjectToXml(pers, fileName);

        Persons unmarshPerson = fromXmlToObject(fileName);
        if (unmarshPerson != null) {
            System.out.println(unmarshPerson.toString());
        }
    }

    private static Persons fromXmlToObject(String filePath) {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            Persons persFromFile = (Persons) un.unmarshal(new FileReader(filePath));
            List<Person> list = persFromFile.getPersons();
            for (Person prs: list) {
                System.out.println(prs.getPersonName() + "," + prs.getPersonBirthDay());
            }
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void convertObjectToXml(Persons pers, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Persons.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(pers, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
