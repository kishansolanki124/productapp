package com.app.productapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.productapp.databinding.ActivityAddProductBinding
import com.app.productapp.db.DatabaseBuilder
import com.app.productapp.db.DatabaseHelperImpl
import com.app.productapp.pojo.ProductListModel
import com.app.productapp.utils.*
import com.app.productapp.viewmodel.ProductViewModel
import com.app.pweapp.apputil.doIfFailure
import com.app.pweapp.apputil.doIfSuccess

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productViewModel = ViewModelProvider(
            this, ViewModelFactory(
                DatabaseHelperImpl(
                    DatabaseBuilder.getInstance(this)
                )
            )
        ).get(ProductViewModel::class.java)

        binding.btAddUpdate.setOnClickListener {
            if (checkFieldsValid()) {
                hideKeyboard(this)
                if (isConnected()) {
                    showProgressDialog()
                    productViewModel.addProduct(
                        ProductListModel.ProductListItem(
                            name = binding.etTitle.text.toString(),
                            adjective = binding.etProductAdjective.text.toString(),
                            material = binding.etProductMaterial.text.toString(),
                            price = binding.etProductPrice.text.toString(),
                            color = binding.etColor.text.toString()
                        )
                    )
                } else {
                    showSnackBar("No Internet Available")
                }
            }
        }

        productViewModel.productItem.observe(this, { result ->
            result.doIfSuccess { items ->
                println(items)
                Handler(Looper.getMainLooper()).postDelayed({
                    setResult(AppConstant.ACTIVITY_RESULT_CODE_100)
                    finish()
                }, 500)
            }

            result.doIfFailure { message, throwable ->
                println("API Error: $throwable,\n\n$message")
                //showSnackBar(message ?: "Unknown error message", this)
            }

            dismissProgressDialog()
        })
    }

    private fun checkFieldsValid(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etTitle.text.toString().trim()) -> {
                showToast(getString(R.string.Enter_Title))
                false
            }

            TextUtils.isEmpty(binding.etColor.text.toString().trim()) -> {
                showToast(getString(R.string.Enter_Color))
                false
            }

            TextUtils.isEmpty(binding.etProductPrice.text.toString().trim()) -> {
                showToast(getString(R.string.Enter_Price))
                false
            }

            (binding.etProductPrice.text.toString().toDouble() <= 0) -> {
                showToast(getString(R.string.Invalid_Price))
                false
            }

            TextUtils.isEmpty(binding.etProductMaterial.text.toString().trim()) -> {
                showToast(getString(R.string.Enter_Material))
                false
            }

            else -> {
                true
            }
        }
    }
}