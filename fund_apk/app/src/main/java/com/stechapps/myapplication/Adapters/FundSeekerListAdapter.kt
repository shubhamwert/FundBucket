package com.stechapps.myapplication.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.stechapps.myapplication.Activities.FundSeekerListActivity
import com.stechapps.myapplication.Activities.FundingActivity
import com.stechapps.myapplication.R
import com.stechapps.myapplication.models.CampaignsRvModel
import com.stechapps.myapplication.models.FundSeekerRvModel
import kotlinx.android.synthetic.main.row_fundseeker_list.view.*
import kotlinx.android.synthetic.main.row_prof_campaign_list.view.*

class FundSeekerListAdapter(private val mContext:Context,private val Dataset:ArrayList<FundSeekerRvModel>):RecyclerView.Adapter<FundSeekerListAdapter.FundSeekerViewHolder>() {

    class FundSeekerViewHolder(v:View):RecyclerView.ViewHolder(v){
        var tvFundSeekerName: TextView =v.tv_fundSeeker_name
        var tvFundSeekerAddress: TextView =v.tv_fundSeeker_add
//        var btremove
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundSeekerViewHolder {
        val v= LayoutInflater.from(mContext).inflate(R.layout.row_fundseeker_list,parent,false)
        return FundSeekerViewHolder(v)
    }

    override fun getItemCount(): Int {
        return Dataset.size
    }

    override fun onBindViewHolder(holder: FundSeekerViewHolder, position: Int) {
        holder.tvFundSeekerName?.text=Dataset.get(position).FundSeeker.toString()
        holder.tvFundSeekerAddress?.text=Dataset.get(position).address.toString()
        holder.itemView.setOnClickListener {
            val intent= Intent(mContext,FundingActivity::class.java)
            intent.putExtra("Campaign",Dataset.get(position).FundSeeker)
            intent.putExtra("addressFundSeeker",Dataset.get(position).address)
            ContextCompat.startActivity(mContext, intent, Bundle.EMPTY)
        }
    }


}