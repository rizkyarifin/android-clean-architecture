package sample.base.app.ui.main

import android.widget.Toast
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.getViewModel
import sample.base.app.R
import sample.base.app.base.BaseActivity
import sample.base.app.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getBindingVariable(): Int = 0
    override fun getVM(): MainViewModel = getViewModel()
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun letStart() {
        initData()
        observeData()
    }

    private lateinit var mainAdapter: MainAdapter

    fun observeData() {
        mViewModel.mDataNews.observe(this, Observer { resource ->
            resource?.let { it ->
                mainAdapter.items = it
                mainAdapter.notifyDataSetChanged()
            }
        })
    }

    fun initData() {
        mainAdapter = MainAdapter(listOf())
//        rv_news.layoutManager = LinearLayoutManager(this)
//        rv_news.adapter = mainAdapter
    }
}