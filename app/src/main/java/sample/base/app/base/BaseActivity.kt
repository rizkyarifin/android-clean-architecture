package sample.base.app.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    lateinit var mBinding : T
    lateinit var mViewModel: V

    abstract fun getBindingVariable() : Int
    abstract fun getViewModel() : V
    abstract fun getLayoutId() : Int
    abstract fun letStart()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        letStart()
    }

    private fun initBinding(){
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mBinding.setVariable(getBindingVariable(), mViewModel)
        mBinding.executePendingBindings()
    }
}