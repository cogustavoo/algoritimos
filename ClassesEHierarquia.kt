/**
 * Programa que implementa classes em diferentes tipos de moradias
 * mostra como:
 * criar uma hierarquia de classe, variaveis e funcoes com heranca
 * classe abstrata, overriding, variaveis publicas e privadas
 */


import kotlin.math.PI
import kotlin.math.sqrt

fun main(args: Array<String>) {
    val squareCabin = SquareCabin(6, 50.0)
    val roundHut = RoundHut(3, 10.0)
    val roundTower = RoundTower(4, 15.5)

    /*
    implementando a fun WITH voce faz com que todas as declaracoes dentro dela pertençam a RoundHut
     */
    with(roundHut) {
        println( "\nRound Hut\n=========")
        println("Material: $buildingMaterial")
        println("Capacity: $capacity")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Has room? ${hasRoom()}")
        getRoom()
        println("Carpet size: %.2f".format(calculateMaxCarpetSize()))
    }
    with(squareCabin) {
        println("\nSquare Cabin\n============")
        println("Capacity: $capacity")
        println("Material: $buildingMaterial")
        println("Has room? ${hasRoom()}")
        println("Floor area: ${floorArea()}")
    }
    with(roundTower) {
        println("\nRound Tower\n==========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
        println("Floor area: %.2f".format(floorArea()))
        println("Carpet size: %.2f".format(calculateMaxCarpetSize()))
    }
}

// classe abstrata e um esquema para montar outras classes, elas herdam as propriedades da classe mae
//dentro de cada subclasse/filha voce pode definir  outros parametros para cada classe
abstract class Dwelling(private var residents: Int){
    abstract val buildingMaterial: String
    abstract val capacity: Int

    /*todos os metodos abstratos definidos em uma classe abstrata precissam ser implementados em
    todas as subclasses dela, antes de executar seu codigo, e necessario implmentar a floorArea()
    nas subclasses
     */
    abstract fun floorArea(): Double
    fun hasRoom(): Boolean {
        return residents < capacity
    }
    fun getRoom() {
        if (capacity > residents){
            residents ++
            println("You got a room!")
        } else {
            println("Sorry, at capaxity and no rooms left.")
        }
    }
}

class SquareCabin(residents: Int, val length: Double) : Dwelling(residents) {
    /*
    É possível sobrescrever uma propriedade da superclasse na subclasse utilizando a palavra reservada override.
    Contudo, assim como as classes,
    propriedades em Kotlin também são final por definição e não podem ser sobrescritas ou
    herdadas sem a substituição explícita do modificador final por open.
     */
    override val buildingMaterial = "wood"
    override val capacity = 6

    override fun floorArea(): Double {
        return length * length
    }
}

open class RoundHut(residents: Int, val radius: Double) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4

// A área útil de uma residência circular é PI * raio^2 ou PI * raio * raio.
    override fun floorArea() : Double {
        return PI * radius * radius
    }
    fun calculateMaxCarpetSize(): Double {
        val diameter = 2 * radius
        return sqrt((diameter * diameter) / 2)
    }
}

// nao prefisa implementar a fun floorArea, essa fun ja e herdada da classe RoundHut
//mas precisa dos argumentos
class RoundTower(
    residents:Int,
    radius: Double,
    private val floors: Int = 2) : RoundHut(residents, radius) {
    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors

    // como a funcao florrArea ja tem definida na classe mae RoundHut
    // podemos pegar os parametros dela e atualizar para a quantidade de andares da RoundTower
    override fun floorArea(): Double {
        return super.floorArea() * floors
    }
}