package ru.cib

data class Hobby(var complexity: Int = 0,
                 var hobby_name: String = "name"){


    override fun toString(): String {
        return "complexity: $complexity, hobby name is: $hobby_name"
    }
}