package com.app.productapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.productapp.databinding.ItemProductBinding
import com.app.productapp.db.entity.Product
import com.app.productapp.pojo.ProductListModel
import com.app.productapp.utils.MyDiffCallback
import com.bumptech.glide.Glide


class ProductListAdapter(private val itemClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductListAdapter.HomeOffersViewHolder>() {

    private var list: ArrayList<Product> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOffersViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeOffersViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: HomeOffersViewHolder, position: Int) {
        holder.bindForecast(list[position])
    }

    fun setItem(employees: List<Product>) {
//        if (this.list.isNotEmpty()) {
//            updateList(productListModel)
//        } else {
//            this.list = productListModel
//            notifyDataSetChanged()
//        }
        updateList(employees)
    }

    override fun getItemCount(): Int = list.size

    class HomeOffersViewHolder(
        private val binding: ItemProductBinding,
        private val itemClick: (Product) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindForecast(
            firebaseMessageModel: Product
        ) {
            with(firebaseMessageModel) {

                binding.product = firebaseMessageModel

//                Glide.with(binding.ivProduct.context)
//                    .load(firebaseMessageModel.image)
//                    .load(firebaseMessageModel.image)
//                    .into(binding.ivProduct)

                itemView.setOnClickListener {
                    itemClick(this)
                }
            }
        }
    }

//    fun updateList(newList: ArrayList<Product>) {
//        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(this.list, newList))
//        diffResult.dispatchUpdatesTo(this)
//    }

    private fun updateList(employees: List<Product>) {
        val diffCallback = MyDiffCallback(this.list, employees)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(employees)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getListSize(): Int{
        return list.size
    }
}