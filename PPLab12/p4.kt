class MapValueFunctor(private val map: MutableMap<Int, String>) {
    fun map(transform: (String) -> String): MapValueFunctor {
        val newMap = map.mapValues { (_, value) -> transform(value) }.toMutableMap()
        return MapValueFunctor(newMap)
    }

    fun get(): MutableMap<Int, String> = map
}


fun String.toPascalCase():
        String = split(" ").joinToString("") {
            it.replaceFirstChar {
                ch -> ch.uppercaseChar()
            }
        }

fun main() {
    val originalMap = mutableMapOf(
        1 to "imi place",
        2 to "sa rezolv",
        3 to "toate problemele da"
    )

    val result = MapValueFunctor(originalMap)
        .map { "Test$it" }
        .map { it.toPascalCase() }
        .get()

    println(result)
}
