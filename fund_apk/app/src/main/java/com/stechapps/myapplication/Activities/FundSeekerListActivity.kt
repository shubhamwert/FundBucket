package com.stechapps.myapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.stechapps.myapplication.Adapters.FundSeekerListAdapter
import com.stechapps.myapplication.R
import com.stechapps.myapplication.models.CampaignsRvModel
import com.stechapps.myapplication.models.FundSeekerRvModel
import kotlinx.android.synthetic.main.activity_fund_seeker_list.*

class FundSeekerListActivity : AppCompatActivity() {
    lateinit var CampName:String
    lateinit var BankAccount:String
    lateinit var FundSeekerAdap:FundSeekerListAdapter
    lateinit var Dataset:ArrayList<FundSeekerRvModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_seeker_list)
        // inside your activity (if you did not enable transitions in your theme)
        val db=FirebaseFirestore.getInstance()
        CampName= intent.extras?.get("Campaign").toString()
        BankAccount= intent.extras?.get("accountFunderCurrent").toString()
        tv_FundSeekerList_campaign.text=CampName
        Dataset= ArrayList()




        val lay=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        rv_fundSeeker_List.layoutManager=lay
        FundSeekerAdap=FundSeekerListAdapter(this,Dataset,BankAccount)
        rv_fundSeeker_List.adapter=FundSeekerAdap

        db.collection("CampaignList").addSnapshotListener { querySnapshot, exception ->
            if (exception!=null){

                Log.d("<<<<<<<<<<<Firebase at Profile>>>>>>>>>",exception.toString())
            }
            else{
                if (querySnapshot != null) {
                    Dataset.removeAll(Dataset)
                    for(i in querySnapshot.documents){
                        Dataset.add(FundSeekerRvModel(i.data?.get("name").toString(),i.data?.get("address").toString()))


                    }
                    FundSeekerAdap.notifyDataSetChanged()
                }
            }



        }


    }

    fun img_profile_goBack(view: View) {
        onBackPressed()
    }

    fun addFundSeekerCampaign(view: View) {
        val int=Intent(this,AddCampaignActivity::class.java)
        int.putExtra("account_name",BankAccount)
        startActivity(int)
    }

}
