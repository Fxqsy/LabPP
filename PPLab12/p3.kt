import kotlin.math.sqrt

data class Point(val x: Double, val y: Double)

fun calculatePerimeter(points: List<Point>): Double {
    val consecutiveDistances = points.zipWithNext { a, b ->
        sqrt((b.x - a.x).pow(2) + (b.y - a.y).pow(2))
    }

    val lastDistance = with(points.first() to points.last()) {
        sqrt((second.x - first.x).pow(2) + (second.y - first.y).pow(2))
    }
    
    return consecutiveDistances.sum() + lastDistance
}

fun main() {
    val n = readLine()!!.toInt()
    val points = List(n) {
        val (x, y) = readLine()!!.split(" ").map { it.toDouble() }
        Point(x, y)
    }

    val perimeter = calculatePerimeter(points)
    println("Perimetrul poligonului este: ${"%.2f".format(perimeter)}")
}