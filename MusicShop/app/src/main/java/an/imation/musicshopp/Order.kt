package an.imation.musicshopp

data class Order(
    var userName: String = "",
    var goodsName: String = "",
    var quantity: Int = 0,
    var price: Double = 0.0,
    var orderPrice: Double = 0.0
)