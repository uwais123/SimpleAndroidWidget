package com.masuwes.simplewidget.widget

import android.app.job.JobParameters
import android.app.job.JobService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.widget.RemoteViews
import com.masuwes.simplewidget.R

class UpdateWidgetService : JobService() {

    override fun onStartJob(jobParameters: JobParameters): Boolean {
        val manager = AppWidgetManager.getInstance(this) // call every context on AppWidgetManager
        val view = RemoteViews(packageName, R.layout.random_numbers_widget) // same with AppUpdateWidget
        val theWidget = ComponentName(this, RandomNumbersWidget::class.java) // value to call everything on RandomNumbersWidget
        val lastUpdate = "Random: " + NumberGenerator.generate(100) // becoming the text on widget and generate number max 100
        view.setTextViewText(R.id.appwidget_text, lastUpdate)
        manager.updateAppWidget(theWidget, view) // use the jobService to control the updateAppWidget
        jobFinished(jobParameters, false)
        return true
    }

    override fun onStopJob(jobParameters: JobParameters): Boolean {
        TODO("Not yet implemented")
    }

}