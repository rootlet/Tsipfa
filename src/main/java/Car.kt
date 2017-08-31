class Car (var color: String, var age: Int, var mas: Int){

    var cost: Int = 0
        get() {
            return field
        }
    set(value) {
        println("yes")
        field = value
    }

    fun nn(){}
}