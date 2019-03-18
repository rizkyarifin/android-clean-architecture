package sample.base.app.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_bar.*
import org.koin.android.viewmodel.ext.android.viewModel
import sample.base.app.R
import sample.base.app.base.BaseActivity
import sample.base.app.base.ResourceState


class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        observeData()
    }

    fun observeData() {
        mainViewModel.mDataNews.observe(this, Observer { resource ->
            resource?.let { it ->
                when (it.state) {
                    ResourceState.LOADING -> showLoading()
                    ResourceState.SUCCESS -> {
                        dismissLoading()
                        it.data.let {
                            mainAdapter.items = it!!
                            mainAdapter.notifyDataSetChanged()
                        }
                    }
                    ResourceState.ERROR -> {
                        dismissLoading()
                        displayError(it.message.toString())
                    }
                }
            }
        })
    }

    private fun displayError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun initData() {
        mainAdapter = MainAdapter(listOf())
        rv_news.layoutManager = LinearLayoutManager(this)
        rv_news.adapter = mainAdapter
    }

    fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    fun dismissLoading() {
        progress_bar.visibility = View.INVISIBLE
    }
}