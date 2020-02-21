package com.stechapps.myapplication.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.JsonObject
import com.stechapps.myapplication.Api.APIinterface
import com.stechapps.myapplication.R
import com.stechapps.myapplication.models.APIaddressrandom
import kotlinx.android.synthetic.main.activity_add_campaign.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddCampaignActivity : AppCompatActivity() {
    lateinit var db:FirebaseFirestore
    lateinit var currentAcc:String
    val apiServe by lazy{
        APIinterface.create()
    }
    val mAuth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_campaign)
        db= FirebaseFirestore.getInstance()
       currentAcc= intent.extras?.get("account_name").toString()
       }

    fun getAddressAndaddCampaign(view: View) {


        val j = JsonObject()
        try {
            j.addProperty("name", ed_add_camp_name.text.toString())
            j.addProperty("account", currentAcc)
        } catch (e: Exception) {
            Log.e("ADD CAMPAIGN ACTIVITY", "makeCall: $e")
        }



        apiServe.RegisterCampaign(j).enqueue(object : Callback<APIaddressrandom>{
            override fun onFailure(call: Call<APIaddressrandom>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    " Contract Not Created ",
                    Toast.LENGTH_SHORT
                ).show()

            }
            override fun onResponse(
                call: Call<APIaddressrandom>,
                response: Response<APIaddressrandom>
            ) {
                val m=HashMap<String,String>()
                m.put("name",ed_add_camp_name.text.toString())
                Log.d("<<<<<<<<<<<<>>>>>>>>>>>>>>>>",response.body()?.account!!.toString())
                m.put("address", response.body()?.account!!.toString())

                m.put("associatedwith",currentAcc)

                db.collection("CampaignList").document(response.body()?.account!!.toString()).set(m as Map<String, Any>).addOnSuccessListener {
                    Toast.makeText(applicationContext," Now you can contribute to this cause ",Toast.LENGTH_SHORT).show()
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(applicationContext,"something went wrong",Toast.LENGTH_SHORT).show()
                }


            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}


