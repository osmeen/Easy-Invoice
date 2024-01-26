package com.osmeen.easyinvoice.ui.theme.data.models

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Customer(
    val name: String = "",
    val address: String = "",
    val phone: String = "",
    val email: String = ""
) : BaseModel() {

    fun getCompleteAddress(): String {
        return "$address\nphone: $phone\nemail: $email"
    }
}

// Inherit the baseModel() class to create a Customer class model