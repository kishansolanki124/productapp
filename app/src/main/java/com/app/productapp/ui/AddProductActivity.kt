package com.app.productapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.productapp.R
import com.app.productapp.databinding.ActivityAddProductBinding
import com.app.productapp.db.DatabaseBuilder
import com.app.productapp.db.DatabaseHelperImpl
import com.app.productapp.db.entity.Product
import com.app.productapp.pojo.ProductListModel
import com.app.productapp.utils.*
import com.app.productapp.viewmodel.ProductViewModel

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private lateinit var productViewModel: ProductViewModel
    private var existingProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (null != intent.getSerializableExtra(AppConstant.ADD_PRODUCT)) {
            existingProduct =
                intent.getSerializableExtra(AppConstant.ADD_PRODUCT) as Product
            binding.product = existingProduct
        }

        initViewModel()
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.btAddUpdate.setOnClickListener {
            if (checkFieldsValid()) {
                hideKeyboard(this)
                if (isConnected()) {
                    showProgressDialog(this)
                    val productModel = ProductListModel.ProductListItem(
                        name = binding.etTitle.text.toString(),
                        adjective = binding.etProductAdjective.text.toString(),
                        material = binding.etProductMaterial.text.toString(),
                        price = binding.etProductPrice.text.toString(),
                        color = binding.etColor.text.toString()
                    )

                    if (null != existingProduct) {
                        productViewModel.editProduct(
                            productModel,
                            existingProduct!!.id
                        )
                    } else {
                        productViewModel.addProduct(
                            productModel
                        )
                    }
                } else {
                    showSnackBar(getString(R.string.no_internet))
                }
            }
        }
    }

    private fun initViewModel() {
        productViewModel = ViewModelProvider(
            this, ViewModelFactory(
                DatabaseHelperImpl(
                    DatabaseBuilder.getInstance(this)
                )
            )
        ).get(ProductViewModel::class.java)

        productViewModel.productItem.observe(this, { result ->
            result.doIfSuccess { items ->
                println(items)
                showToast(getString(R.string.Product_save_success))
                Handler(Looper.getMainLooper()).postDelayed({
                    setResult(AppConstant.ACTIVITY_RESULT_CODE_100)
                    finish()
                }, 500)
            }

            result.doIfFailure { message, throwable ->
                println("API Error: $throwable,\n\n$message")
                showSnackBar(message ?: "Unknown error message")
            }

            dismissProgressDialog()
        })
    }

    private fun checkFieldsValid(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etTitle.text.toString().trim()) -> {
                showSnackBar(getString(R.string.Enter_Title))
                false
            }

            TextUtils.isEmpty(binding.etColor.text.toString().trim()) -> {
                showSnackBar(getString(R.string.Enter_Color))
                false
            }

            TextUtils.isEmpty(binding.etProductPrice.text.toString().trim()) -> {
                showSnackBar(getString(R.string.Enter_Price))
                false
            }

            (binding.etProductPrice.text.toString().toDouble() <= 0) -> {
                showSnackBar(getString(R.string.Invalid_Price))
                false
            }

            TextUtils.isEmpty(binding.etProductAdjective.text.toString().trim()) -> {
                showSnackBar(getString(R.string.Enter_Adjective))
                false
            }

            TextUtils.isEmpty(binding.etProductMaterial.text.toString().trim()) -> {
                showSnackBar(getString(R.string.Enter_Material))
                false
            }

            else -> {
                true
            }
        }
    }
}