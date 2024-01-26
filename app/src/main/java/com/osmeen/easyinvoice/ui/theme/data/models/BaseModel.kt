package com.osmeen.easyinvoice.ui.theme.data.models

import com.google.firebase.firestore.Exclude
import com.osmeen.easyinvoice.ui.theme.data.utils.currentDateTime

abstract class BaseModel(
    @get:Exclude
    open var id: String = ""
) {
    var createdAt: Long = currentDateTime
    var updatedAt: Long = currentDateTime
}
// The model Holds the common Properties for each model class