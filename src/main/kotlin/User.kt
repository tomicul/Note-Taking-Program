package org.example

import java.time.LocalDateTime

class User (
    val name: String = readln(),
    var notes: MutableList<Note> = mutableListOf(),
    var currentNote: Note = Note("", "", Date(0, 0, 0), 0, "")
)
{
    fun createNote()
    {
        println("Care este titlul notitei?")
        val title: String = readln()

        if (title != "")
        {
            val author: String = this.name
            val localTime: LocalDateTime = LocalDateTime.now()
            val date: Date = Date(localTime.year, localTime.monthValue, localTime.dayOfMonth)
            val hourOfCreation: Int = localTime.hour

            println("Care este continutul notitei?")
            val content: String = readln()

            val note = Note(
                title,
                author,
                date,
                hourOfCreation,
                content
            )

            notes.add(note)
        }
        else
        {
            println("Numele notitei este nul")
        }
    }

    fun deleteNote(manager: NoteManager)
    {
        manager.deleteNote(this.name)
    }

    fun deleteNoteCurrentSession()
    {
        println("Titlul notitei de sters (din sesiunea curenta): ")
        var delTitle: String = readln()

        var index: Int = -1
        for (note in notes) {
            if (note.title == delTitle) {
                index = notes.indexOf(note)
                break
            }
        }

        if (index != -1)
        {
            notes.removeAt(index)
        }
    }

    fun showNotes(manager: NoteManager)
    {
        var cnt: Int = 1;
        println("\t -Notitele aflate in directorul cu notite")
        if (this.name == "admin")
        {
            manager.showList()
        }
        else
        {
            manager.showListUsername(this.name)
        }

        println("\n\n\t-Notitele create in sesiunea curenta de catre utilizator")
        cnt = 1
        if (notes.size == 0)
        {
            println("Nu exista notite in sesiunea curenta")
        }
        else
        {
            for (note in this.notes)
            {
                println(cnt.toString() + ":")
                println(note.toString())
                println()

                cnt++
            }
        }
    }

    fun changeCurrentNote()
    {
        var cnt: Int = 1;
        for (note in notes)
        {
            println(cnt.toString() + ": " + note.title)
            cnt++
        }

        println("Notita pe care vreti sa o selectati: ")
        try {
            var index: Int = readln().toInt()

            if (index >= 1 && index < cnt)
            {
                this.currentNote = notes[index - 1]
            }
            else
            {
                println("Notita aleasa nu exista")
            }
        }
        catch (e: Exception)
        {
            println("Nu s-a dat un numar")
        }
    }

    fun uploadNote(manager: NoteManager)
    {
        println("\tIncarcare Notita: ")
        this.changeCurrentNote()

        if (this.currentNote.author != "")
        {
            manager.listNote += currentNote
            manager.update()
        }
        else
        {
            println("Nu s-a putut incarca notita deoarce nu exista")
        }
    }
}