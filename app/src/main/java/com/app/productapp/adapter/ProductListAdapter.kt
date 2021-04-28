package com.app.productapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.productapp.databinding.ItemProductBinding
import com.app.productapp.pojo.ProductListModel

class ProductListAdapter(private val itemClick: (ProductListModel.ProductListItem) -> Unit) :
    RecyclerView.Adapter<ProductListAdapter.HomeOffersViewHolder>() {

    private var list: ArrayList<ProductListModel.ProductListItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOffersViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeOffersViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: HomeOffersViewHolder, position: Int) {
        holder.bindForecast(list[position])
    }

    fun setItem(list: ArrayList<ProductListModel.ProductListItem>) {
        //todo use diffutils to add only those values that are not available
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class HomeOffersViewHolder(
        private val binding: ItemProductBinding,
        private val itemClick: (ProductListModel.ProductListItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindForecast(
            firebaseMessageModel: ProductListModel.ProductListItem
        ) {
            with(firebaseMessageModel) {

                binding.product = firebaseMessageModel

                itemView.setOnClickListener {
                    itemClick(this)
                }
            }
        }
    }
}