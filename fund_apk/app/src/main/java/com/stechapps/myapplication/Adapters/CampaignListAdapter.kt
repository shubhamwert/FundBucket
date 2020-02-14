package com.stechapps.myapplication.Adapters

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.stechapps.myapplication.Activities.FundSeekerListActivity
import com.stechapps.myapplication.R
import com.stechapps.myapplication.models.CampaignsRvModel
import kotlinx.android.synthetic.main.row_prof_campaign_list.view.*

class CampaignListAdapter(private val mContext:Context,private val Dataset:ArrayList<CampaignsRvModel>):RecyclerView.Adapter<CampaignListAdapter.CampaignViewHolder>() {

    class CampaignViewHolder(v:View):RecyclerView.ViewHolder(v){
        var tvCampaignName=v.tv_cont_list_heading
        var tvAddress=v.tv_cont_list_address
//        var btremove
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampaignViewHolder {
        val v= LayoutInflater.from(mContext).inflate(R.layout.row_prof_campaign_list,parent,false)
        return CampaignViewHolder(v)
    }

    override fun getItemCount(): Int {
        return Dataset.size
    }

    override fun onBindViewHolder(holder: CampaignViewHolder, position: Int) {
        holder.tvCampaignName?.text=Dataset.get(position).CampaignName.toString()
        holder.tvAddress?.text=Dataset.get(position).address.toString()
        holder.itemView.setOnClickListener {
            val intent= Intent(mContext,FundSeekerListActivity::class.java)
            intent.putExtra("Campaign",Dataset.get(position).CampaignName)
            intent.putExtra("addressFundSeeker",Dataset.get(position).address)


            startActivity(mContext,intent, Bundle.EMPTY)
        }


    }


}