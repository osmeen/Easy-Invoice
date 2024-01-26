package com.osmeen.easyinvoice.ui.theme.data

import java.lang.Exception

//This is a class which gives a result
//<out R> means is a generic type which can be used everywhere
sealed class Resource <out R>{
    data class Success<out R>(val result: R): Resource<R>()
    data class Failure(val exception: Exception): Resource<Nothing>()
    //To show the progress when the user logs in
    object Loading: Resource<Nothing>()
}