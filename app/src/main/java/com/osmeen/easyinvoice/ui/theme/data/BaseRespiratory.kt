package com.osmeen.easyinvoice.ui.theme.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.osmeen.easyinvoice.ui.theme.data.models.BaseModel

// This is a generic class and for the implementation of this class I need to define the type of the model class
//I have define a BaseModel because all the model in our project are inheriting the BaseModel Class
abstract class BaseRepository<T : BaseModel>(
    auth: FirebaseAuth,
    firestore: FirebaseFirestore,
    dbNode: String
// Every repository will fetch data from the firestore
) {
    private val currentUser = auth.currentUser ?: throw IllegalStateException("User not logged in")
    protected val db = firestore.collection(currentUser.uid).document(dbNode).collection(dbNode)

    fun getData(snapshot: QuerySnapshot, model: Class<T>): List<T> {
        return snapshot.map {
            it.toObject(model).also { model ->
                model.id = it.id
                model.createdAt = it.data["createdAt"].toString().toLong()
                model.updatedAt = it.data["updatedAt"].toString().toLong()
            }
        }
    }
}