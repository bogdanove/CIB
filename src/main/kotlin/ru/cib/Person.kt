package ru.cib

import java.util.*
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlElementWrapper
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter


@XmlAccessorType(XmlAccessType.FIELD)
class Person {
    @XmlElement var name: String? = null
    @XmlJavaTypeAdapter(DateAdapter::class)
    var birthday: Date? = null
    @XmlElementWrapper(name = "hobbies")
    @XmlElement(name = "hobby")
    var hobbies: List<Hobby>? = null

    override fun toString(): String {
        return "Name: $name, Birthday: $birthday, Hobby: $hobbies"
    }
}