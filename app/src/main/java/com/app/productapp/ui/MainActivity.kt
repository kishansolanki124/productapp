package com.app.productapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.productapp.adapter.ProductListAdapter
import com.app.productapp.databinding.ActivityMainBinding
import com.app.productapp.db.DatabaseBuilder
import com.app.productapp.db.DatabaseHelperImpl
import com.app.productapp.db.entity.Product
import com.app.productapp.utils.*
import com.app.productapp.viewmodel.ProductViewModel
import com.app.productapp.utils.doIfFailure
import com.app.productapp.utils.doIfSuccess

class MainActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        setupRecyclerView()
        setupListener()
        getProducts()
    }

    private fun getProducts() {
        showProgressDialog(this)
        productViewModel.fetchFromDB()
    }

    private fun setupListener() {
        binding.fbAdd.setOnClickListener {
            startActivityForResult(
                Intent(this, AddProductActivity::class.java),
                AppConstant.ACTIVITY_RESULT_CODE_100
            )
        }
    }

    private fun setupRecyclerView() {
        setRecyclerViewLayoutManager(binding.rvProducts)

        productListAdapter = ProductListAdapter {
            startActivityForResult(
                Intent(this, AddProductActivity::class.java).putExtra(
                    AppConstant.ADD_PRODUCT,
                    it
                ), AppConstant.ACTIVITY_RESULT_CODE_100
            )
        }
        binding.rvProducts.adapter = productListAdapter
    }

    private fun initViewModel() {
        productViewModel = ViewModelProvider(
            this, ViewModelFactory(
                DatabaseHelperImpl(
                    DatabaseBuilder.getInstance(this)
                )
            )
        ).get(ProductViewModel::class.java)

        productViewModel.productListModel.observe(this, { result ->
            result.doIfSuccess { items ->
                productListAdapter.setItem(items)
            }
        })

        productViewModel.networkProductList.observe(this, { result ->
            result.doIfSuccess { items ->
                val reverse: List<Product> = items.reversed()
                productListAdapter.setItem(reverse)
            }

            result.doIfFailure { message, throwable ->
                println("API Error: $throwable,\n\n$message")
                showSnackBar(message ?: "Unknown error message")
            }

            dismissProgressDialog()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppConstant.ACTIVITY_RESULT_CODE_100) {
            showProgressDialog(this)
            productViewModel.fetchFromServer()
        }
    }
}