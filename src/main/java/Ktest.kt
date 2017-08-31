
fun main(args: Array<String>) {
    var name: String = "Sergey"
    var a: Int = 1
    val b: Int = 2
    val message = "result is  + ${a+b} + $name"
    println(message)

    val car = Car("red", 5, 10)
    car.cost = 55
    println(car.cost)
}