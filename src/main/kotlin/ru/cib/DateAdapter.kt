package ru.cib

import java.text.SimpleDateFormat
import java.util.*
import javax.xml.bind.annotation.adapters.XmlAdapter

class DateAdapter : XmlAdapter<String, Date>() {



    private val format = "yyyy-MM-dd"

    override fun unmarshal(value: String?): Date? {
        return SimpleDateFormat(format).parse(value)
    }

    override fun marshal(value: Date?): String? {
        return SimpleDateFormat(format).format(value)
    }
}