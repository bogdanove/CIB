import com.sun.xml.internal.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlRootElement(name = "Person")
//@XmlType(propOrder = {"name", "birthday"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    private String name;
    private String birthday;

    @XmlElementWrapper(name = "Persons")
    private List<Person> persons;

    public Person() {
    }

    public Person(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getPersonName() {
        return name;
    }

    public void setPersonName(String name) {
        this.name = name;
    }

    public String getPersonBirthDay() {
        return birthday;
    }

    public void setEmpName(String birthday) {
        this.birthday = birthday;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Person {" +
                "name = '" + name + '\'' +
                ", birthday = " + birthday +
                '}';
    }
}
