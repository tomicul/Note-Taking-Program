package org.example

import java.time.LocalDateTime

class Note (
    val title: String,
    val author: String,
    val date: Date,
    val hourOfCreation: Int,
    val content: String,
)
{
    override fun toString(): String {
        var str: String = "Title: " + title
        str += "\nAuthor: " + author
        str += "\nDate: " + date.day + " " + date.month + " " + date.year
        str += "\nHour: " + hourOfCreation.toString()
        str += "\n\n\n"
        str += content

        return str
    }
}