package com.derrick.park.assignment3_contacts.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.derrick.park.assignment3_contacts.R
import com.derrick.park.assignment3_contacts.models.Contact
import java.util.regex.Matcher

class DetailActivity : AppCompatActivity() {
    lateinit var fnameEditText: EditText
    lateinit var lnameEditText: EditText
    lateinit var cellEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        contact = intent.getParcelableExtra("contact")

//        val nameEditText: EditText = findViewById(R.id.nameEditText)
//        val cellEditText: EditText = findViewById(R.id.cellEditText)
//        val emailEditText: EditText = findViewById(R.id.emailEditText)
        fnameEditText = findViewById(R.id.fnameEditText)
        lnameEditText = findViewById(R.id.lnameEditText)
        cellEditText = findViewById(R.id.cellEditText)
        emailEditText = findViewById(R.id.emailEditText)

        val addressEditText: EditText = findViewById(R.id.addressEditText)

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            if (setData())
                finish()
        }

        fnameEditText.setText(contact.name?.first)
        lnameEditText.setText(contact.name?.last)
        cellEditText.setText(contact.cell)
        emailEditText.setText(contact.email)
        addressEditText.setText(contact.location.toString())

        // Editing address is not available yet
        addressEditText.isEnabled = false

    }

    fun setData(): Boolean {
        if (/*validateCell(cellEditText.text.toString()) && */validateEmail(emailEditText.text.toString())) {
            contact.name?.first = fnameEditText.text.toString().capitalize()
            contact.name?.last = lnameEditText.text.toString().capitalize()
            contact.cell = cellEditText.text.toString()
            contact.email = emailEditText.text.toString()

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("contact", contact)
            setResult(Activity.RESULT_OK, intent)

            return true
        } else {
            Toast.makeText(this, "Wrong format", Toast.LENGTH_SHORT).show()
            return false
        }
    }

//    fun sendData(){
//        val intent = Intent(this, DetailActivity::class.java)
//        intent.putExtra("contact", Contact())
//        finishActivity()
//    }

    companion object {
        val VALID_EMAIL_ADDRESS_REGEX = Regex(
                "/^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*\$/",
                RegexOption.IGNORE_CASE)
        val VALID_CELL_NUMBER_REGEX = Regex(
                "/^((([0-9]{1})*[- .(]*([0-9]{3})[- .)]*[0-9]{3}[- .]*[0-9]{4})+)*\$/")
    }

    fun validateEmail(email: String): Boolean {
        val matcher: Matcher = VALID_EMAIL_ADDRESS_REGEX.toPattern().matcher(email)
        return matcher.find()
    }

    fun validateCell(number: String): Boolean {
        val matcher: Matcher = VALID_CELL_NUMBER_REGEX.toPattern().matcher(number)
        return matcher.find()
    }

}