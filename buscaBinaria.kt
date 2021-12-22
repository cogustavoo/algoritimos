fun main() {
    val test = intArrayOf(1, 3, 5, 6)
    val target: Int = 1

    println(test.size)
    println(searchInsert(test, target))
}

fun searchInsert(nums: IntArray, target: Int): Int {

    var final = nums.size - 1
    var inicio = 0

    while (inicio < final) {
        var meio = (inicio + final) / 2
        if (nums[meio] > target) {
            final = meio - 1
        } else if (nums[meio] < target) {
            inicio = meio + 1
        } else {
            return meio
        }
    }
    return inicio
}
