package com.derrick.park.assignment3_contacts.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.derrick.park.assignment3_contacts.R
import com.derrick.park.assignment3_contacts.models.Contact
import com.derrick.park.assignment3_contacts.models.ContactList
import com.derrick.park.assignment3_contacts.network.ContactClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.ArrayList

class MainActivity : AppCompatActivity(), OnCHolderClickListener {

    private val mContactList: ArrayList<Contact> = ArrayList(0)
    private lateinit var mRecyclerView: RecyclerView

    private var selected: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.cRecyclerView)

        val call = ContactClient.getContacts(20)

        call.enqueue(object : Callback<ContactList> {
            override fun onResponse(call: Call<ContactList>, response: Response<ContactList>) {
                if (response.isSuccessful) {
                    mContactList.addAll(response.body()?.contactList!!)
                    mContactList.sortBy { it.name!!.first }
                }

                val adapter = CAdapter(this@MainActivity, mContactList)
                mRecyclerView.adapter = adapter
                mRecyclerView.layoutManager = LinearLayoutManager(parent)
            }

            override fun onFailure(call: Call<ContactList>, t: Throwable) {
                // Error Handling
                t.printStackTrace()
            }
        })
    }

    override fun onClick(view: View, item: Contact) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("contact", item)
        selected = item
        startActivityForResult(intent, GO_BACK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            mContactList.remove(selected)
            mContactList.add(data!!.getParcelableExtra("contact"))
            mContactList.sortBy { it.name!!.first }
            mRecyclerView.adapter?.notifyDataSetChanged()
        }
        selected = null
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
        val GO_BACK: Int = 1
    }

}