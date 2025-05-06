package org.example

import java.io.File

fun pathToNoteList() : MutableList<Note>
{
    val paths: List<String> = File("src/main/NotesPath").readLines()

    var listNote: MutableList<Note> = mutableListOf()

    try {
        for (path in paths)
        {
            if (path != "") listNote.add(pathToNote(path))
        }
    }
    catch (e: Exception)
    {
        println("Managerul curent este gol")
    }

    return listNote
}

fun pathToNote(path: String) : Note
{
    try {
        val fileText: List<String> = File(path).readLines()

        var title: String = ""
        var author: String = ""
        var date: Date = Date(0, 0, 0)
        var hour: Int = 0
        var content: String = ""

        val titleRegex: Regex = Regex("Title: ")
        val authorRegex: Regex = Regex("Author: ")
        val dateRegex: Regex = Regex("Date: ")
        val hourRegex: Regex = Regex("Hour: ")

        for (line in fileText)
        {
            var flag: Boolean = false
            if (line != "")
            {
                if (line.contains(titleRegex) == true)
                {
                    title = line.replace(titleRegex, "")
                    flag = true
                }

                if (line.contains(authorRegex) == true)
                {
                    author = line.replace(authorRegex, "")
                    flag = true
                }

                if (line.contains(dateRegex) == true)
                {
                    val lineCopy = line.replace(dateRegex, "")
                    val list = lineCopy.split(" ")

                    date = Date(list[2].toInt(), list[1].toInt(), list[0].toInt())
                    flag = true
                }

                if (line.contains(hourRegex) == true)
                {
                    hour = line.replace(hourRegex, "").toInt()
                    flag = true
                }

                if (flag == false)
                {
                    content += line
                }
            }
        }

        return Note(title, author, date, hour, content)

    }
    catch (e: Exception)
    {
        return Note("", "", Date(0, 0, 0), 0, "")
    }
}

class NoteManager (
    var listNote: MutableList<Note> = pathToNoteList()
)
{
    fun update()
    {
        var notesPaths: String = ""
        for (note in listNote)
        {
            File("src/main/Notes/" + note.title + "-" + note.author + ".txt").writeText(note.toString())
            notesPaths += "src/main/Notes/" + note.title + "-" + note.author + ".txt\n"
        }

        File("src/main/NotesPath").writeText(notesPaths)
    }

    fun showList()
    {
        var cnt: Int = 1
        for (note in listNote)
        {
            println(cnt.toString() + ":")
            println(note.toString())
            println()

            cnt++
        }
    }

    fun showListUsername(username: String)
    {
        if (this.listNote.size == 0)
        {
            println("Nu exista notite in director")
        }
        else
        {
            var cnt: Int = 1
            for (note in this.listNote)
            {
                if (note.author == username)
                {
                    println(cnt.toString() + ":")
                    println(note.toString())
                    println()

                    cnt++
                }
            }
        }
    }

    fun deleteNote(username: String)
    {
        var index: Int = -1

        if (username == "admin")
        {
            println("Numele autorului notitei de sters: ")
            val author: String = readln()

            println("Titlul notitei de sters: ")
            val title: String = readln()

            for (note in listNote)
            {
                if (note.title == title && note.author == author)
                {
                    File("src/main/Notes/" + note.title + "-" + note.author + ".txt").delete()
                    index = listNote.indexOf(note)
                    break
                }
            }
        }
        else
        {
            println("Titlul notitei de sters: ")
            val title: String = readln()


            for (note in listNote)
            {
                if (note.title == title && note.author == username)
                {
                    File("src/main/Notes/" + note.title + "-" + note.author + ".txt").delete()
                    index = listNote.indexOf(note)
                    break
                }
            }


        }

        if (index != -1)
        {
            listNote.removeAt(index)
        }

        this.update()
    }
}