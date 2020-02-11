package com.stechapps.myapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.stechapps.myapplication.Adapters.CampaignListAdapter
import com.stechapps.myapplication.R
import com.stechapps.myapplication.models.CampaignsRvModel
import kotlinx.android.synthetic.main.activity_profile_funder.*

class ProfileFunder : AppCompatActivity() {
lateinit var FunderDataSet:ArrayList<CampaignsRvModel>
    lateinit var CampaignAdapter : CampaignListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_funder)
        FunderDataSet=ArrayList<CampaignsRvModel>()
        FunderDataSet.add(CampaignsRvModel("sample data","sampleData"))
        CampaignAdapter= CampaignListAdapter(this,FunderDataSet)
        val ll=LinearLayoutManager(this)
        rv_CampaignList.layoutManager=ll
        rv_CampaignList.adapter=CampaignAdapter

    }
}
