package sample.base.app.ui.main

import android.util.Log
import sample.base.app.base.BaseViewModel
import sample.base.app.data.network.repository.Repository
import sample.base.app.utils.rx.SchedulerProvider
import sample.base.app.utils.ext.with
import java.lang.System.err

class MainViewModel(private val repo : Repository,
                    private val scheduler: SchedulerProvider) : BaseViewModel(){

    fun getNews(){
        launch {

            repo.getNews().with(scheduler).subscribe(
                {
                    Log.d("Test", "Success")
                },
                { err ->
                    err.printStackTrace()
                    Log.d("Test", "Error")
                })
        }
    }

}