package com.stechapps.myapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.stechapps.myapplication.R
import kotlinx.android.synthetic.main.activity_funding.*

class FundingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funding)
    }

    fun donate(view: View) {
        Toast.makeText(this,"Donated",Toast.LENGTH_SHORT).show()
        pb_fund_collected.progress++
    }
}
