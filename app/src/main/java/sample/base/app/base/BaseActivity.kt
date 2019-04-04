package sample.base.app.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import sample.base.app.utils.ext.hasNetwork
import sample.base.app.utils.ext.showToast


abstract class BaseActivity<B : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(), BaseFragment.Callback {

    lateinit var mBinding: B
    lateinit var mViewModel: V

    abstract fun getBindingVariable(): Int
    abstract fun getVM(): V
    abstract fun getLayoutId(): Int
    abstract fun letStart()

    val isNetworkConnected: Boolean
        get() = hasNetwork(applicationContext)!!

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
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

    private fun initDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (::mViewModel.isInitialized) mViewModel else getVM()
        mBinding.setVariable(getBindingVariable(), mViewModel)
        mBinding.executePendingBindings()
    }
}