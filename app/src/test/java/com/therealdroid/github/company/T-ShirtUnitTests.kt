//package com.therealdroid.github.company
//
//import com.therealdroid.github.company.domain.models.Product
//import org.junit.Test
//
//import org.junit.Assert.*
//
///**
// * Example local unit test, which will execute on the development machine (host).
// *
// * See [testing documentation](http://d.android.com/tools/testing).
// */
//class `T-ShirtUnitTests` {
//
//    @Test
//    fun test_t_shirt_discount() {
//        val shirt = Product()
//        shirt.price = 20F
//        shirt.amount = 3
//        shirt.code = "voucher"
//        shirt.name = "cabify voucher"
//
//        assertEquals(shirt.calcTotalPriceDiscount(), 57F)
//    }
//
//    @Test
//    fun test_t_shirt_no_discount() {
//        val shirt = Product()
//        shirt.price = 20F
//        shirt.amount = 2
//        shirt.code = "voucher"
//        shirt.name = "cabify voucher"
//
//        assertEquals(shirt.calcTotalPriceDiscount(), 40F)
//    }
//
//    @Test
//    fun test_t_shirt_discount_five() {
//        val shirt = Product()
//        shirt.price = 20F
//        shirt.amount = 25
//        shirt.code = "TSHIRT"
//        shirt.name = "cabify t-shirt"
//
//        assertEquals(shirt.calcTotalPriceDiscount(), 475F)
//    }
//}
