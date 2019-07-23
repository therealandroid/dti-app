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
//class VoucherUnitTests {
//
//    @Test
//    fun test_voucher_should_have_two_discounts_of_five() {
//        val voucher = Product()
//        voucher.price = 7.5F
//        voucher.amount = 5
//        voucher.code = "voucher"
//        voucher.name = "cabify voucher"
//
//       assertEquals(voucher.calcTotalPriceDiscount(), 22.5F)
//    }
//
//    @Test
//    fun test_voucher_should_have_two_discounts_of_four() {
//        val voucher = Product()
//        voucher.price = 7.5F
//        voucher.amount = 4
//        voucher.code = "voucher"
//        voucher.name = "cabify voucher"
//
//        assertEquals(voucher.calcTotalPriceDiscount(), 15F)
//    }
//
//    @Test
//    fun test_voucher_should_have_no_discounts() {
//        val voucher = Product()
//        voucher.price = 7.5F
//        voucher.amount = 0
//        voucher.code = "voucher"
//        voucher.name = "cabify voucher"
//
//        assertEquals(voucher.calcTotalPriceDiscount(), 0F)
//    }
//
//    @Test
//    fun test_voucher_should_have_one_discounts() {
//        val voucher = Product()
//        voucher.price = 7.5F
//        voucher.amount = 3
//        voucher.code = "voucher"
//        voucher.name = "cabify voucher"
//
//        assertEquals(voucher.calcTotalPriceDiscount(), 15F)
//    }
//
//    @Test
//    fun test_voucher_should_have_many_discounts() {
//        val voucher = Product()
//        voucher.price = 7.0F
//        voucher.amount = 10
//        voucher.code = "voucher"
//        voucher.name = "cabify voucher"
//
//       print(voucher.calcTotalPriceDiscount())
//    }
//}
