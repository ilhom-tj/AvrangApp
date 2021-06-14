package tj.colibri.avrang.data.checkout.Spiner

data class ChekOutData(
    val orderNumber : String,
    val totalPrice : Double,
    val deliverySum : Double,
    val discountFromBonus : Double,
    val paymentMethod : String,
    val bonusForPurchase : Double,
    val totalForPay : Double
)