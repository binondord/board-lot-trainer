package com.randybinondo.boardlottrainerforphstocks.ui.tables

data class MenuItem(
    val name: String,
    val minimum: Double,
    val maximum: Double,
    val fluctuation: Double,
    val boardLot: Int,
    var isSelected: Boolean = false
)

object DataProvider {

    val boardlots = listOf(
        MenuItem("0.0001 - 0.0099", 0.0001, 0.0099, 0.0001, 1000000),
        MenuItem("0.01 - 0.049", 0.01, 0.049, 0.001, 100000),
        MenuItem("0.05 - 0.249", 0.05, 0.249, 0.001, 10000),
        MenuItem("0.25 - 0.495", 0.25, 0.495, 0.005, 10000),
        MenuItem("0.5 - 4.99", 0.5, 4.99, 0.01, 1000),
        MenuItem("5 - 9.99", 5.00, 9.99, 0.01, 100),
        MenuItem("10 - 0.0099", 10.00, 19.98, 0.02, 100),
        MenuItem("20 - 49.95", 20.00, 49.95, 0.05, 100),
        MenuItem("50 - 99.95", 50.00, 99.95, 0.05, 10),
        MenuItem("100 - 199.9", 100.00, 199.9, 0.1, 10),
        MenuItem("200 - 499.8", 200.00, 499.8, 0.2, 10),
        MenuItem("500 - 999.5", 500.00, 999.5, 0.5, 10),
        MenuItem("1000 - 1999", 1000.00, 1999.00, 1.00, 5),
        MenuItem("2000 - 4998", 2000.00, 4998.00, 2.00, 5),
        MenuItem("5000.00 - 1000000.00", 5000.00, 1000000.00, 5.00, 5),
    )

    val usdboardLots = listOf(
        MenuItem("0.0001 - 0.0099", 0.00, 0.99, 0.01, 100),
        MenuItem("0.0001 - 0.0099", 1.00, 4.99, 0.01, 20),
        MenuItem("0.0001 - 0.0099", 5.00, 9.99, 0.01, 10),
        MenuItem("0.0001 - 0.0099", 10.00, 19.98, 0.02, 10),
        MenuItem("0.0001 - 0.0099", 20.00, 49.95, 0.05, 10),
        MenuItem("0.0001 - 0.0099", 50.00, 99.95, 0.05, 5),
        MenuItem("0.0001 - 0.0099", 100.00, 199.9, 0.1, 5),
        MenuItem("0.0001 - 0.0099", 200.00, 499.8, 0.2, 5),
        MenuItem("0.0001 - 0.0099", 500.00, 999.5, 0.5, 5),
        MenuItem("0.0001 - 0.0099", 1000.00, 1000000.00, 1.00, 5),
    )
}