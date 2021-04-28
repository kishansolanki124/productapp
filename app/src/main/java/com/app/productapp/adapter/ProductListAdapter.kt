package com.app.productapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.productapp.databinding.ItemProductBinding
import com.app.productapp.db.entity.Product
import com.app.productapp.utils.ProductDiffCallback


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
//        list = employees as ArrayList<Product>
//        notifyDataSetChanged()

        val diffCallback = ProductDiffCallback(list, employees)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(employees)
        diffResult.dispatchUpdatesTo(this)
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
}