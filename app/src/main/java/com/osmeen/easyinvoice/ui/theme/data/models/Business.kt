package com.osmeen.easyinvoice.ui.theme.data.models

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Business(
//   I Defined Default Values bcs in firestore we have to have an empty constructor
    val name: String = "",
    val address: String = "",
    val phone: String = "",
    val email: String = ""
) : BaseModel() {

    fun getCompleteAddress(): String {
        return "$address\nphone: $phone\nemail: $email"
    }
}

// Inherit the baseModel() class to create a business class model