package ru.cib

import java.util.*
import javax.xml.bind.annotation.*
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter


@XmlAccessorType(XmlAccessType.FIELD)
class Person {
    @XmlTransient var id: Int? = 0
    @XmlElement var name: String? = null
    @XmlJavaTypeAdapter(DateAdapter::class)
    var birthday: Date? = null
    @XmlElementWrapper(name = "hobbies")
    @XmlElement(name = "hobby")
    var hobbies: List<Hobby>? = null

    override fun toString(): String {
        return "Id: $id, Name: $name, Birthday: $birthday, Hobby: $hobbies"
    }
}