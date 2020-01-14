package com.derrick.park.assignment3_contacts.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class ContactList {
    @SerializedName("results")
    @Expose
    val contactList: ArrayList<Contact>? = null
}