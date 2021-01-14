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

    val hobby = Hobby(1, 1, "Walking")
    val hobby1 = Hobby(2, 2, "Run")
    val hobby2 = Hobby(2, 3, "FastRun")


    val hobbies0 = listOf(hobby)
    val hobbies1 = listOf(hobby1, hobby2)

    val person = Person().apply {
        //id = 1
        name = "Ivanova Nina Ivanovna"
        birthday = GregorianCalendar(2000, 11, 11).time
        hobbies = hobbies0
    }
    val person1 = Person().apply {
        //id = 2
        name = "Sidorov Viktor Petrovich"
        birthday = GregorianCalendar(2002, 10, 10).time
        hobbies = hobbies1
    }
    val list = listOf(person, person1)
    val persons = Persons()

    persons.persons = list

    convertObjectToXml(persons, fileName)

    persons.persons = fetchData(fetchDataPerson(), fetchDataHobby())

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
        //println(it.toString())
    }
    return list
}


@Throws(SQLException::class)
fun fetchDataPerson(): List<Person>? {

    val SQL__QUERY = "select * from person"
    val con = DataSource.getConnection()
    val pst = con.prepareStatement(SQL__QUERY)
    val rs = pst.executeQuery()
    val prs = mutableListOf<Person>()

    while (rs.next()) {

        val person = Person().apply {
            id = rs.getInt("id")
            name = rs.getString("name")
            birthday = rs.getDate("birthday")
        }
        prs.add(person)
    }

    return prs
}

@Throws(SQLException::class)
fun fetchDataHobby(): List<Hobby>?{

    val SQL__QUERY_ = "select * from hobby"
    val con = DataSource.getConnection()
    val pst1 = con.prepareStatement(SQL__QUERY_)
    val rst = pst1.executeQuery()
    val listHobby = mutableListOf<Hobby>()
    while (rst.next()) {

            val hobby = Hobby().apply {
                personId = rst.getInt("person_id")
                complexity = rst.getInt("complexity")
                hobby_name = rst.getString("hobby_name")
            }
            listHobby.add(hobby)
    }

    return listHobby
}

fun fetchData(plist: List<Person>?, hList: List<Hobby>?): List<Person>? {
    plist?.forEach{person ->
        val hobbylist = mutableListOf<Hobby>()
        hList?.forEach { hobby ->
            if (person.id == hobby.personId) {
                hobbylist.add(hobby)
            }
        }
        person.hobbies = hobbylist
    }
    return plist
}

@Throws(SQLException::class)
fun writeData(list: List<Person>) {
    val insertQuery = "insert into person(id, name, birthday) values(default, ?, ?)"
    val con = DataSource.getConnection()
    val pst = con.prepareStatement(insertQuery)


    list.forEach {
        pst.setString(1, it.name)
        pst.setDate(2, java.sql.Date(it.birthday!!.time))
        pst.executeUpdate()
    }

//    val insertQueryHobby = "insert into hobby(id, person_id, complexity, hobby_name) values(default, ?, ?, ?)"
//    val conh = DataSource.getConnection()
//    val psth = conh.prepareStatement(insertQueryHobby)
//
//        .forEach {
//        psth.setInt(2, it.hobbies.personId)
//    }
}




