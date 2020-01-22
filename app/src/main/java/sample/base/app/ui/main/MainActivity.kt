package sample.base.app.ui.main

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import sample.base.app.BR
import sample.base.app.R
import sample.base.app.base.BaseActivity
import sample.base.app.databinding.ActivityMainBinding
import sample.base.app.utils.view.State


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getVM(): MainViewModel = getViewModel()
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun letStart() {
        initData()
        observeData()
        initState()
    }

    private lateinit var mainAdapter: MainAdapter

    private fun observeData() {
        mViewModel.dataNews.observe(this, Observer { resource ->
            mainAdapter.submitList(resource)
        })
    }

    private fun initData() {
        mainAdapter = MainAdapter { mViewModel.retry() }
        rv_news.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_news.adapter = mainAdapter
    }

    private fun initState() {
        txt_error.setOnClickListener { mViewModel.retry() }
        mViewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility = if (mViewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (mViewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
            if (!mViewModel.listIsEmpty()) {
                mainAdapter.setState(state ?: State.DONE)
            }
        })
    }
}