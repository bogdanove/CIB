import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class Persons {


    @XmlElementWrapper(name = "Persons")
    @XmlElement(name ="person")
    private List<Person> persons;


    public Persons() {

    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}

