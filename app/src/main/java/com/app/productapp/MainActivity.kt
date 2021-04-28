package com.app.productapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.productapp.adapter.ProductListAdapter
import com.app.productapp.databinding.ActivityMainBinding
import com.app.productapp.db.DatabaseBuilder
import com.app.productapp.db.DatabaseHelperImpl
import com.app.productapp.utils.*
import com.app.productapp.viewmodel.ProductViewModel
import com.app.pweapp.apputil.doIfFailure
import com.app.pweapp.apputil.doIfSuccess

class MainActivity : AppCompatActivity() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productViewModel = ViewModelProvider(
            this, ViewModelFactory(
                DatabaseHelperImpl(
                    DatabaseBuilder.getInstance(this)
                )
            )
        ).get(ProductViewModel::class.java)

        productViewModel.gameListResponseResponseModel.observe(this, { result ->

            result.doIfSuccess { items ->
                productListAdapter.setItem(items)
                //showToast("updated item ${items.size}")
            }

            result.doIfFailure { message, throwable ->
                println("API Error: $throwable,\n\n$message")
                //showSnackBar(message ?: "Unknown error message", this)
            }

            dismissProgressDialog()
        })

        setRecyclerViewLayoutManager(binding.rvProducts)

        productListAdapter = ProductListAdapter {

        }
        binding.rvProducts.adapter = productListAdapter

        showProgressDialog()
        productViewModel.fetchProductWithCoupon()

        binding.fbAdd.setOnClickListener {
            startActivityForResult(
                Intent(this, AddProductActivity::class.java),
                AppConstant.ACTIVITY_RESULT_CODE_100
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppConstant.ACTIVITY_RESULT_CODE_100) {
            showProgressDialog()
            productViewModel.fetchProductWithCoupon()
        }
    }
}