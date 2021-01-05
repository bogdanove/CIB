package ru.cib

import java.io.File
import java.io.FileReader
import java.sql.SQLException
import java.util.*
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller


fun main(args: Array<String>) {
    val fileName = "src/main/kotlin/ru/cib/file/file.xml"
    val fileName1 = "src/main/kotlin/ru/cib/file/file1.xml"

    val hobby = Hobby(1, "Walking")
    val hobby1 = Hobby(2, "Run")
    val hobby2 = Hobby(3, "FastRun")


    val hobbies0 = listOf(hobby)
    val hobbies1 = listOf(hobby1, hobby2)

    val person = Person().apply {
        name = "Ivanova Nina Ivanovna"
        birthday = GregorianCalendar(2000, 11, 11).time
        hobbies = hobbies0
    }
    val person1 = Person().apply {
        name = "Sidorov Viktor Petrovich"
        birthday = GregorianCalendar(2002, 10, 10).time
        hobbies = hobbies1
    }
    val list = listOf(person, person1)
    val persons = Persons()

    persons.persons = list

    convertObjectToXml(persons, fileName)

    persons.persons = fetchData()

    convertObjectToXml(persons, fileName1)

    fromXmlToObject(fileName)

    writeData(fromXmlToObject(fileName))
}

fun convertObjectToXml(persons: Persons, fileName: String) {
    val context = JAXBContext.newInstance(Persons::class.java)
    val marshaller = context.createMarshaller()
    // устанавливаем флаг для читабельного вывода XML в JAXB
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    // маршаллинг объекта в файл
    marshaller.marshal(persons, File(fileName))

}

fun fromXmlToObject(fileName: String): List<Person> {
    val context = JAXBContext.newInstance(Persons::class.java)
    val un = context.createUnmarshaller()
    val list = mutableListOf<Person>()

    val persFromFile = un.unmarshal(FileReader(fileName)) as Persons
    persFromFile.persons?.forEach {
        list.add(it)
        println(it.toString())
    }
    return list
}


@Throws(SQLException::class)
fun fetchData(): List<Person>? {
    val SQL__QUERY = "select * from person, hobby"
    val prs = mutableListOf<Person>()
    val con = DataSource.getConnection()
    val pst = con.prepareStatement(SQL__QUERY)
    val rs = pst.executeQuery()

    while (rs.next()) {

        val person = Person().apply {
            name = rs.getString("name")
            birthday = rs.getDate("birthday")
            hobbies = listOf(Hobby().apply {
                complexity = rs.getInt("complexity")
                hobby_name = rs.getString("hobby_name")
            })
        }
        prs.add(person)
    }
    println(prs.toString())
    return prs
}

@Throws(SQLException::class)
fun writeData(list: List<Person>) {
    val insertQuery = "insert into person(name, birthday) values(?, ?)"
    val con = DataSource.getConnection()
    val pst = con.prepareStatement(insertQuery)


    list.forEach {
        pst.setString(1, it.name)
        pst.setDate(2, java.sql.Date(it.birthday!!.time))
        pst.executeUpdate()
    }
}




