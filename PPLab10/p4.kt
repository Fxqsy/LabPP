import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.io.File

// simulare fisiere
data class FileTask(val fileName: String, val number: Int)

fun main() = runBlocking {
    val taskChannel = Channel<FileTask>()

    val workers = List(4) { id ->
        launch {
            for (task in taskChannel) {
                val result = processTask(task.number)
            }
        }
    }

    launch {
        repeat(10) { i ->
            val fileName = "fisier_$i.txt"
            val value = i + 1
            taskChannel.send(FileTask(fileName, value))
        }
        taskChannel.close()
    }

    workers.forEach { it.join() }

    println("Toate sarcinile au fost procesate.")
}

fun processTask(n: Int): Long {
    var sum = 0L
    for (i in 0..n) {
        sum += i
    }
    return sum
}
