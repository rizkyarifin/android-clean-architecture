package sample.base.app.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    lateinit var mBinding : T
    lateinit var mViewModel: V

    abstract fun getBindingVariable() : Int
    abstract fun getVM() : V
    abstract fun getLayoutId() : Int
    abstract fun letStart()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        letStart()

        mViewModel.apply {

        }
    }

    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (::mViewModel.isInitialized) mViewModel else getVM()
        mBinding.setVariable(getBindingVariable(), mViewModel)
        mBinding.executePendingBindings()
    }
}