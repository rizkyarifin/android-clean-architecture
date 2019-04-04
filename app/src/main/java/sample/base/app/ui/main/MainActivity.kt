package sample.base.app.ui.main

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import sample.base.app.BR
import sample.base.app.R
import sample.base.app.base.BaseActivity
import sample.base.app.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getVM(): MainViewModel = getViewModel()
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun letStart() {
        initData()
        observeData()
    }

    private lateinit var mainAdapter: MainAdapter

    fun observeData() {
        mViewModel.mDataNews.observe(this, Observer { resource ->
            resource?.let {
                mainAdapter.items = it
                mainAdapter.notifyDataSetChanged()
            }
        })
    }

    fun initData() {
        mainAdapter = MainAdapter(listOf())
        rv_news.layoutManager = LinearLayoutManager(this)
        rv_news.adapter = mainAdapter
    }
}