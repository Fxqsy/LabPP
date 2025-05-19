import java.io.File
import java.lang.Exception

fun caesarEncrypt(word: String, offset: Int): String {
    val result = StringBuilder()

    for (char in word) {
        when {
            char.isUpperCase() -> {
                val base = 'A'.code
                val encryptedChar = ((char.code - base + offset) % 26 + base).toChar()
                result.append(encryptedChar)
            }
            char.isLowerCase() -> {
                val base = 'a'.code
                val encryptedChar = ((char.code - base + offset) % 26 + base).toChar()
                result.append(encryptedChar)
            }
            else -> {
                result.append(char)
            }
        }
    }
    return result.toString()
}

fun processLine(line: String, offset: Int): String {
    val words = line.split(" ")

    return words.joinToString(" ") { word ->
        var startIndex = 0
        var endIndex = word.length - 1

        while (startIndex < word.length && !word[startIndex].isLetterOrDigit()) {
            startIndex++
        }

        while (endIndex >= 0 && !word[endIndex].isLetterOrDigit()) {
            endIndex--
        }

        if (startIndex > endIndex) {
            word
        } else {
            val prefix = word.substring(0, startIndex)
            val coreWord = word.substring(startIndex, endIndex + 1)
            val suffix = word.substring(endIndex + 1)

            if (coreWord.length in 4..7) {
                prefix + caesarEncrypt(coreWord, offset) + suffix
            } else {
                word
            }
        }
    }
}

fun main() {
    print("numele fisierului: ")
    val inputFileName = readLine() ?: return

    print("offset: ")
    val offset = readLine()?.toIntOrNull()

    if (offset == null) {
        println("Offset invalid.")
        return
    }

    try {
        val inputFile = File(inputFileName)
        if (!inputFile.exists()) {
            println("Fisierul nu exista.")
            return
        }

        val outputFile = File("criptat_output.txt")
        inputFile.bufferedReader().useLines { lines ->
            outputFile.bufferedWriter().use { writer ->
                lines.forEach { line ->
                    val processedLine = processLine(line, offset)
                    writer.write(processedLine)
                    writer.newLine()
                }
            }
        }

        println("da")

    } catch (e: Exception) {
        println("Eroare: ${e.message}")
    }
}
