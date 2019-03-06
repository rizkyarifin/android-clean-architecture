package sample.base.app.ui.main

import android.arch.lifecycle.Observer
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_bar.*
import org.koin.android.viewmodel.ext.android.viewModel
import sample.base.app.R
import sample.base.app.base.BaseActivity
import sample.base.app.base.BaseState
import sample.base.app.data.model.Article

class MainActivity : BaseActivity() {

    private val mainViewModel : MainViewModel by viewModel()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        observeData()
    }

    fun observeData() {
        mainViewModel.getNewsData().observe(this, Observer { it ->
            when (it) {
                is BaseState.Error -> {
                    dismissLoading()
                    Log.d("Main", "Error")
                }
                is BaseState.Data<*> -> {
                    dismissLoading()
                    mainAdapter.items = it.data as List<Article>
                    mainAdapter.notifyDataSetChanged()
                }
                is BaseState.Loading -> showLoading()
            }
        })
    }

    fun initData(){
        mainAdapter = MainAdapter(listOf())
        rv_news.layoutManager = LinearLayoutManager(this)
        rv_news.adapter = mainAdapter
    }

    fun showLoading(){
        progress_bar.visibility = View.VISIBLE
    }

    fun dismissLoading(){
        progress_bar.visibility = View.INVISIBLE
    }
}
