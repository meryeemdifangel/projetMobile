package com.example.movieexample


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri

class Util {

    @SuppressLint("WrongConstant")
    fun openFacebook(ctx: Context, url: String) {
        val facebookUrl = "https://www.facebook.com/"
        try{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            ctx.startActivity(intent)
        }
        catch(e :Exception){
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
           intent.data = Uri.parse(facebookUrl)
        }
    }

    fun openGmailApp(context: Context) {
        val intent = context.packageManager.getLaunchIntentForPackage("com.google.android.gm")
        intent?.let {
            context.startActivity(it)
        } ?: run {
            // Gmail app is not installed, open Gmail website in a browser
            val gmailUrl = "https://mail.google.com/mail/"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(gmailUrl))
            context.startActivity(browserIntent)
        }
    }


    fun callPhoneNumber(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun openGoogleMaps(context: Context, latitude: Double, longitude: Double) {
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
        val intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        intent.setPackage("com.google.android.apps.maps")
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun addToCart(context: Context, menuId:Int, quantity:Int) {
        val instance = AppDatabase.buildDatabase(context)
        instance?.getMenuDao()?.addToCart(menuId,quantity)
    }

    fun deleteCart(context: Context){
        val instance = AppDatabase.buildDatabase(context)
        instance?.getMenuDao()?.deleteCart()
    }

    fun deleteItemFromCart(context: Context, menuId:Int){
        val instance = AppDatabase.buildDatabase(context)
        instance?.getMenuDao()?.deleteMenuFromCart(menuId)
    }

    fun viewCart(context: Context):MutableList<Menu>?{
        val instance = AppDatabase.buildDatabase(context)
        return instance?.getMenuDao()?.getCart()
    }

    fun getTotal(context: Context):Double{
        val instance = AppDatabase.buildDatabase(context)
        val items = instance?.getMenuDao()?.getCart()!!
        var total:Double = 0.0
        for(item in items){
            total += item.price*item.quantity
        }
        return total
    }

}