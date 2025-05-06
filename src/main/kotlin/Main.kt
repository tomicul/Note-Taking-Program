package org.example

fun clear()
{
    println("\u001B[H")
    println("\u001B[2J")
    println("\u001B[H")
    System.out.flush()
}

fun pressEnter()
{
    println()
    println("Apasati enter ca sa reveniti la meniu")
    readln()
}

fun printInterface(username: String)
{
    println("Nume utilizator: " + username + "\n")
    println("\u001b[1;32m")
    println("\t1: Afiseaza lista de notite")
    println("\t2: Incarca o notita")
    println("\t3: Creeaza o noua notita")
    println("\t4: Sterge o notita")
    println()
    println("\t0: EXIT")
    println()
}

fun main() {
    println("Dati numele utilizatorului: ")
    val user: User = User()
    var code: String = "-1"

    val manager: NoteManager = NoteManager()

    while (true)
    {
        clear()
        printInterface(user.name)
        code = readln()

        clear()
        println("\u001b[0m")
        when (code)
        {
            "1" -> user.showNotes(manager)

            "2" -> user.uploadNote(manager)

            "3" -> user.createNote()

            "4" ->
            {
                println("1: Din sesiunea curenta")
                println("2: Lista incarcata")

                val code2: String = readln()

                clear()
                when (code2)
                {
                    "1" -> user.deleteNoteCurrentSession()

                    "2" -> user.deleteNote(manager)

                    else -> println("Nu s-a selectat o optiune buna")
                }
            }

            "0" -> break

            else -> println("Nu s-a selectat o optiune buna")
        }
        pressEnter()
    }
}