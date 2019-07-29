package com.yl.kot.view.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.yl.kot.R
import com.yl.kot.base.BaseRecyclerAdapter
import com.yl.kot.base.BaseViewHolder
import com.yl.kot.data.entity.Banner

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */
class BannerAdapter : BaseRecyclerAdapter<BannerAdapter.BannerViewHolder, Banner>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (super.getItemCount() < 2) {
            super.getItemCount()
        } else Int.MAX_VALUE
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.onDataBinding(mData[position % mData.size])
    }

    inner class BannerViewHolder(itemView: View) : BaseViewHolder<Banner>(itemView) {

        private lateinit var ivPicture: ImageView

        override fun onViewBinding(itemView: View) {
            ivPicture = itemView.findViewById(R.id.iv_banner_picture)
        }

        override fun onDataBinding(data: Banner) {
            Glide.with(ivPicture).load(data.imagePath).into(ivPicture)
        }
    }
}