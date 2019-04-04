package sample.base.app.ui.main

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.huteri.analogclock.AnalogClockView
import me.huteri.analogclock.ArcSlice
import sample.base.app.R
import java.text.DateFormat
import java.util.*


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.MINUTE, 0)

        val arcSlice = ArcSlice()
        calendar.set(Calendar.HOUR_OF_DAY, 10)
        arcSlice.startTime = calendar.getTime()

        calendar.set(Calendar.HOUR_OF_DAY, 14)

        arcSlice.endTime = calendar.getTime()
        arcSlice.color = Color.parseColor("#e74c3c")

        val arcSlice2 = ArcSlice()
        calendar.set(Calendar.HOUR_OF_DAY, 15)

        arcSlice2.startTime = calendar.getTime()
        calendar.set(Calendar.HOUR_OF_DAY, 16)

        arcSlice2.endTime = calendar.getTime()
        arcSlice2.color = Color.parseColor("#16a085")

        val arcSlice3 = ArcSlice()
        calendar.set(Calendar.HOUR_OF_DAY, 17)

        arcSlice3.startTime = calendar.getTime()

        calendar.set(Calendar.HOUR_OF_DAY, 20)

        arcSlice3.endTime = calendar.getTime()
        arcSlice3.color = Color.parseColor("#f1c40f")

        val arcSliceList = arrayListOf<ArcSlice>()
        arcSliceList.add(arcSlice)
        arcSliceList.add(arcSlice2)
        arcSliceList.add(arcSlice3)

        val analogClockView = findViewById<AnalogClockView>(R.id.analogClockView)
        analogClockView.list = arcSliceList
        analogClockView.onSliceClickListener = object : AnalogClockView.SliceClickListener {
            override fun onSliceClick(pos: Int) {
                Toast.makeText(
                    this@Main2Activity,
                    "Clicked on slice at " + DateFormat.getTimeInstance().format(arcSliceList.get(pos).getStartTime()),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
