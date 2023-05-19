package com.example.movieexample

class MenuItemViewModel(count: Int, price: Double) {
    private lateinit var viewModel: MenuItemViewModel

    private var count: Int = count
    private var price: Double = price
    fun getCount(): Int {
        return count
    }
    fun setCount(count:Int) {
        this.count = count
    }
    fun getPrice(): Double {
        return price
    }
    fun setPrice(price:Double){
        this.price = price
    }
    fun incrementCount() {
        count++
    }

    fun decrementCount() {
        if (count > 0) {
            count--
        }
    }

    fun incrementQuantity() {
        TODO("Not yet implemented")
    }

    fun decrementQuantity() {
        TODO("Not yet implemented")
    }
}
