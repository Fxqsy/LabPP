
interface AndGate {
    fun calculateOutput(): Boolean
}

interface LogicImplementation {
    fun compute(inputs: List<Boolean>): Boolean
}

class AndLogicFSM : LogicImplementation {
    override fun compute(inputs: List<Boolean>): Boolean {
        var currentState = true
        for (input in inputs) {
            currentState = currentState && input
            if (!currentState) break
        }
        return currentState
    }
}

class AndGateBuilder(private val logic: LogicImplementation) {
    private val inputs = mutableListOf<Boolean>()

    fun addInput(input: Boolean): AndGateBuilder {
        inputs.add(input)
        return this
    }

    fun build(): AndGate {
        return ConcreteAndGate(inputs, logic)
    }
}
class ConcreteAndGate(private val inputs: List<Boolean>, private val logic: LogicImplementation ) : AndGate {
    override fun calculateOutput(): Boolean {
        return logic.compute(inputs)
    }
}

fun main() {
    val logic = AndLogicFSM()

    // Poarta AND cu 8 intrari
    val gate = AndGateBuilder(logic)
        .addInput(true)
        .addInput(false)
        .addInput(true)
        .addInput(true)
        .addInput(false)
        .addInput(true)
        .addInput(true)
        .addInput(true)


        .build()

    println("Rezultat poarta AND: ${gate.calculateOutput()}")
}
