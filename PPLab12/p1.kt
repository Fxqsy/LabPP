fun main() {
    val lista = listOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)

    val rezultat = lista
        .filter { it >= 5 }              
        .chunked(2)
        .filter { it.size == 2 }
        .map { (a, b) -> a * b }
        .sum()

    println("Rezultat final: $rezultat")
}