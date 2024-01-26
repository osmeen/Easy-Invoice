package com.osmeen.easyinvoice.ui.theme.data.models

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Tax(
    val desc: String = "",
    val value: Double = 0.0,
) : BaseModel() {

    val taxLabel
        get() = "$desc ($value%)"

    val taxValue
        get() = "$value%"
}

// Inherit the baseModel() class to create a Tax class model