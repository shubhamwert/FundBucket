package com.stechapps.myapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.stechapps.myapplication.Adapters.CampaignListAdapter
import com.stechapps.myapplication.Api.APIinterface
import com.stechapps.myapplication.R
import com.stechapps.myapplication.models.CampaignsRvModel
import com.stechapps.myapplication.models.FunderApiModel
import kotlinx.android.synthetic.main.activity_profile_funder.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback as Callback1

class ProfileFunder : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    val apiServe by lazy {
        APIinterface.create()
    }
    val mAuth by lazy {
        FirebaseAuth.getInstance()
    }
    lateinit var FunderDataSet: ArrayList<CampaignsRvModel>
    lateinit var CampaignAdapter: CampaignListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_funder)
        mAuth.addAuthStateListener {
            if(mAuth.currentUser==null){
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
        else{

        val call = apiServe.checkConnectivity().enqueue(object : Callback1<FunderApiModel> {
            override fun onFailure(call: Call<FunderApiModel>, t: Throwable) {
                Log.d("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>", "$t")
            }

            override fun onResponse(
                call: Call<FunderApiModel>,
                response: Response<FunderApiModel>
            ) {
                if (response.body()?.response!!) {
                    Toast.makeText(applicationContext, "Connected to Server ", Toast.LENGTH_SHORT)
                        .show()
                    initFunder()
                    val x="${mAuth.currentUser?.email.toString()} \n ${mAuth.currentUser?.uid?.slice(IntRange(0,12))} ..."
                    tv_prof_description.text=x
                }
            }
        })}


    }}


    fun initFunder() {


        FunderDataSet = ArrayList()


        db = FirebaseFirestore.getInstance()

        FunderDataSet.add(CampaignsRvModel("sample data", "sampleData"))
        CampaignAdapter = CampaignListAdapter(this, FunderDataSet)
        val ll = LinearLayoutManager(this)
        rv_CampaignList.layoutManager = ll
        rv_CampaignList.adapter = CampaignAdapter
        db.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid.toString()).collection("Accounts")
            .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException!=null){

                    Log.d("<<<<<<<<<<<Firebase at Profile>>>>>>>>>",firebaseFirestoreException.toString())
                }
                else{
                    if (querySnapshot != null) {
                        FunderDataSet.removeAll(FunderDataSet)
                        for(i in querySnapshot.documents){
                            FunderDataSet.add(CampaignsRvModel(i.data?.get("name").toString(),i.data?.get("account").toString()))


                        }
                        CampaignAdapter.notifyDataSetChanged()
                    }
                }
            }
    }

    fun addCampaign(view: View) {
        startActivity(Intent(this,AddNewAccountActivity::class.java))
    }

    fun signOut(view: View) {

        mAuth.signOut()
    }


}
