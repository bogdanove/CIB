import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    private String name;
    private String birthday;

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

}
