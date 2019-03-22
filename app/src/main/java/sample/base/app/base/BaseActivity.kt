package sample.base.app.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.Observer
import sample.base.app.utils.ext.showToast


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    lateinit var mBinding: T
    lateinit var mViewModel: V

    abstract fun getBindingVariable(): Int
    abstract fun getVM(): V
    abstract fun getLayoutId(): Int
    abstract fun letStart()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        letStart()
        handleMessage()
    }

    private fun handleMessage() {
        mViewModel.apply {
            showMessage.observe(this@BaseActivity, Observer {
                if (it != null) {
                    showToast(it)
                }
            })
        }
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (::mViewModel.isInitialized) mViewModel else getVM()
        mBinding.setVariable(getBindingVariable(), mViewModel)
        mBinding.executePendingBindings()
    }
}