package com.sokheang.project2322.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.sokheang.project2322.R
import com.sokheang.project2322.adapter.TransactionAdapter
import com.sokheang.project2322.databinding.ActivityMainBinding
import com.sokheang.project2322.databinding.ActivityOverviewBinding
import com.sokheang.project2322.domain.Profile
import java.util.Calendar
import java.util.Date
import java.util.Locale

class OverviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initChart()
        setVariable()

        binding.layoutBack.setOnClickListener {
            finish()
        }
    }

    private fun setVariable() {
        val profile: Profile = intent.getSerializableExtra("profile_data") as Profile
        Log.e("data profile", profile.toString())

        val transactionAdapter = TransactionAdapter(profile.transaction)
        binding.transactionList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.transactionList.adapter = transactionAdapter
    }

    private fun initChart() {
        val values = listOf(150f, 200f, 50f, 100f, 250f, 80f, 300f)
        val entries = mutableListOf<BarEntry>()
        for(i in values.indices) {
            entries.add(BarEntry(i.toFloat(),values[i]))
        }

        val barDataSet = BarDataSet(entries, "Statistics")
        val colors = MutableList(values.size){Color.GRAY}
        colors[values.lastIndex] = resources.getColor(R.color.green)
        barDataSet.colors = colors

        val data = BarData(barDataSet)
        data.barWidth = 0.7f
        binding.overviewChart.description.isEnabled = false
        binding.overviewChart.isEnabled = false
        binding.overviewChart.data = data

        val daysOfWeek = getLast7DaySourName()
        val xAxis = binding.overviewChart.xAxis
        xAxis.textColor = Color.WHITE
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return daysOfWeek.getOrNull(value.toInt()) ?: value.toString()
            }
        }

        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawLabels(false)
        xAxis.granularity = 1f
        xAxis.labelCount = daysOfWeek.size
        xAxis.setDrawGridLines(false)

        binding.overviewChart.axisRight.isEnabled = false
        binding.overviewChart.invalidate()
    }

    private fun getLast7DaySourName(): List<String> {
        return if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.O){
            val today = java.time.LocalDate.now()
            (6 downTo 0).map { offset ->
                today.minusDays(offset.toLong())
                    .dayOfWeek
                    .getDisplayName(java.time.format.TextStyle.SHORT, java.util.Locale.ENGLISH)
            }
        } else {
            val calendar = java.util.Calendar.getInstance()
            val format = java.text.SimpleDateFormat("EEE", Locale.ENGLISH)
            val days = mutableListOf<String>()
            for(i in 6 downTo 0) {
                calendar.time = Date()
                calendar.add(Calendar.DAY_OF_YEAR, -i)
                days.add(format.format(calendar.time))
            }
            days
        }
    }
}