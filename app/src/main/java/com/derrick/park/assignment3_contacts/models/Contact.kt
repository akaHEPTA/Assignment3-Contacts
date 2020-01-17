package com.derrick.park.assignment3_contacts.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Parcelize
class Contact(
        @SerializedName("gender")
        @Expose
        val gender: String? = null,
        @SerializedName("name")
        @Expose
        var name: Name? = null,
        @SerializedName("location")
        @Expose
        val location: Location? = null,
        @SerializedName("email")
        @Expose
        var email: String? = null,
        @SerializedName("cell")
        @Expose
        var cell: String? = null
) : Parcelable {

    override fun toString(): String {
        return String.format("%n%s%n%s%n%s%n%s", name, location, email, cell)
    }

    /**
     * Name {first: , last: }
     */
    @Parcelize
    class Name(
            @SerializedName("first")
            @Expose
            var first: String? = null,
            @SerializedName("last")
            @Expose
            var last: String? = null,
            @SerializedName("title")
            @Expose
            val title: String? = null
    ) : Parcelable {

        override fun toString(): String {
            return "$first $last"
        }
    }

    /**
     * Location {street: , city: , state: , postcode: }
     */
    @Parcelize
    class Location(
            @SerializedName("street")
            @Expose
            private val street: Street? = null,
            @SerializedName("city")
            @Expose
            private val city: String? = null,
            @SerializedName("state")
            @Expose
            private val province: String? = null,
            @SerializedName("postcode")
            @Expose
            private val postcode: String? = null
    ) : Parcelable {

        override fun toString(): String {
            return "$street, $city, $province Canada $postcode"
        }

        @Parcelize
        class Street(
                @SerializedName("number")
                @Expose
                private val number: String? = null,
                @SerializedName("name")
                @Expose
                private val name: String? = null
        ) : Parcelable {

            override fun toString(): String {
                return "$name $number"
            }
        }
    }
}