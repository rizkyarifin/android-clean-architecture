package sample.base.app.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.viewmodel.ext.android.viewModel
import sample.base.app.R

class MainActivity : AppCompatActivity() {

    val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getNews()
    }
}
