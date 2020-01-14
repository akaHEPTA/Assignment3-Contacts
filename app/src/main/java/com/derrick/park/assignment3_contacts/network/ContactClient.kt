package com.derrick.park.assignment3_contacts.network

import com.derrick.park.assignment3_contacts.models.ContactList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ContactClient {
    private const val ROOT_URL: String = "https://randomuser.me/api/"
    private val retrofitInstance: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    private val randomUserService: RandomUserService
        get() {
            return retrofitInstance.create(RandomUserService::class.java)
        }

    fun getContacts(num: Int): Call<ContactList> {
        return randomUserService.listContacts(num)
    }
}