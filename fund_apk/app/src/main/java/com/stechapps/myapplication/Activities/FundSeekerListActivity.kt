package com.stechapps.myapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.stechapps.myapplication.Adapters.FundSeekerListAdapter
import com.stechapps.myapplication.R
import com.stechapps.myapplication.models.FundSeekerRvModel
import kotlinx.android.synthetic.main.activity_fund_seeker_list.*

class FundSeekerListActivity : AppCompatActivity() {
    lateinit var CampName:String
    lateinit var CampAddress:String
    lateinit var FundSeekerAdap:FundSeekerListAdapter
    lateinit var Dataset:ArrayList<FundSeekerRvModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_seeker_list)
        // inside your activity (if you did not enable transitions in your theme)
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

            // set an exit transition
            exitTransition = Explode()
        }

        CampName= intent.extras?.get("Campaign").toString()
        CampAddress= intent.extras?.get("addressFundSeeker").toString()
        tv_FundSeekerList_campaign.text=CampName
        Dataset= ArrayList()

        Dataset.add(FundSeekerRvModel("hello","0xh2hhee2hhqh21"))
        Dataset.add(FundSeekerRvModel("hello","0xh2hhee2hhqh21"))
        Dataset.add(FundSeekerRvModel("hello","0xh2hhee2hhqh21"))



        val lay=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        rv_fundSeeker_List.layoutManager=lay
        rv_fundSeeker_List.adapter=FundSeekerListAdapter(this,Dataset)



    }

    fun img_profile_goBack(view: View) {
        onBackPressed()
    }
}
