package ru.cib

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlTransient

@XmlAccessorType(XmlAccessType.FIELD)
data class Hobby(
    @XmlTransient var personId: Int = 0,
    var complexity: Int = 0,
    var hobby_name: String = "name"
) {


    override fun toString(): String {
        return "personId: $personId, complexity: $complexity, hobby name is: $hobby_name"
    }
}