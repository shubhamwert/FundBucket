package com.stechapps.myapplication.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.stechapps.myapplication.Api.APIinterface
import com.stechapps.myapplication.R
import com.stechapps.myapplication.models.APIaddressrandom
import kotlinx.android.synthetic.main.activity_funding.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.JsonObject
import com.stechapps.myapplication.models.CampaignsRvModel
import kotlin.reflect.typeOf


class FundingActivity : AppCompatActivity() {
    val target=100000000
    var current_value=0
    lateinit var db:FirebaseFirestore
    val apiServe by lazy {
        APIinterface.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funding)
        db= FirebaseFirestore.getInstance()
        campaign_name.text=intent.extras!!["Campaign"]?.toString()
        pb_funding_wait.visibility=View.VISIBLE

        db.collection("CollectedFunds").document(intent.extras!!["Campaign"].toString()).addSnapshotListener { snapshot, exception ->

            if(exception != null){
                return@addSnapshotListener
            }
            if(snapshot?.get("value") !=null)
                current_value= snapshot["value"]?.toString()?.toInt()!!

            pb_fund_collected.setProgress(current_value.toInt(),true)
            pb_funding_wait.visibility=View.GONE

        }

        pb_fund_collected.max=target
        check_donate_register_as_Funder.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                pb_funding_wait.visibility=View.VISIBLE


                val j = JsonObject()
                try {

                    j.addProperty("names", intent.extras!!["Campaign"]?.toString())
                    j.addProperty("account",intent.extras!!["currentAcc"]?.toString())
                    j.addProperty("contract", intent.extras!!["addressContract"]?.toString())
                } catch (e: Exception) {
                    Log.e("ADD CAMPAIGN ACTIVITY", "makeCall: $e")
                }
                apiServe.RegisterAsFunder(j).enqueue(object : Callback<APIaddressrandom> {
                    override fun onFailure(call: Call<APIaddressrandom>, t: Throwable) {
                        Toast.makeText(applicationContext,"unable to register",Toast.LENGTH_SHORT).show()

                    }

                    override fun onResponse(
                        call: Call<APIaddressrandom>,
                        response: Response<APIaddressrandom>
                    ) {if(response.isSuccessful){
                        if(response.body()?.response!!){
                         Toast.makeText(applicationContext,"well you are registereed",Toast.LENGTH_SHORT).show()
                         check_donate_register_as_Funder.isChecked=true
                         check_donate_register_as_Funder.isClickable=false
                         ed_value_funding.isClickable=true}}
                        pb_funding_wait.visibility=View.GONE


                    }

                })

            }

        }


    }

    fun donate(view: View) {

        pb_funding_wait.visibility=View.VISIBLE
        if(ed_value_funding.text.toString().isEmpty()){
            Toast.makeText(this," Enter A valid value ",Toast.LENGTH_SHORT).show()
            return
        }

        val j = JsonObject()
        try {

            j.addProperty("names", intent.extras!!["Campaign"]?.toString())
            j.addProperty("account",intent.extras!!["currentAcc"]?.toString())
            j.addProperty("contract", intent.extras!!["addressContract"]?.toString())
            j.addProperty("value",ed_value_funding.text.toString())
        } catch (e: Exception) {
            Log.e("ADD CAMPAIGN ACTIVITY", "makeCall: $e")
        }

        apiServe.Donate(j).enqueue(object :Callback<APIaddressrandom>{
            override fun onFailure(call: Call<APIaddressrandom>, t: Throwable) {
                Toast.makeText(applicationContext,"unable to Donate",Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(
                call: Call<APIaddressrandom>,
                response: Response<APIaddressrandom>
            ) { if(response.isSuccessful){
                if(response.body()?.response!!){
                val h= HashMap<String,Int>()

                     val p=(current_value+ed_value_funding.text.toString().toInt())
                h["value"] = p
                db.collection("CollectedFunds").document(intent.extras!!["Campaign"].toString()).set(
                    h as Map<String, Any>
                ).addOnSuccessListener {
                    Toast.makeText(applicationContext,"Donated",Toast.LENGTH_SHORT).show()
}
                    pb_funding_wait.visibility=View.GONE

                }

            }


        }})


    }
}
