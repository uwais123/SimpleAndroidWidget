package com.masuwes.simplewidget.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.masuwes.simplewidget.R

/**
 * Implementation of App Widget functionality.
 */
class RandomNumbersWidget : AppWidgetProvider() {

    companion object {
        private const val WIDGET_CLICK = "widgetsclick"
        private const val WIDGET_ID_EXTRA = "widget_id_extra"
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

        val lastUpdate = "Random: " + NumberGenerator.generate(100) // generate random number maximum 100
        val widgetText = context.getString(R.string.appwidget_text)

        // this value will call to be a text on appwidget_text
        val views = RemoteViews(context.packageName, R.layout.random_numbers_widget)
        views.setTextViewText(R.id.appwidget_text, lastUpdate)
        views.setOnClickPendingIntent(R.id.btn_click, getPendingSelfIntent(context, appWidgetId, WIDGET_CLICK))

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        // this method is same with the updateAppWidget, the different is on the onClick PendingIntent
        if (WIDGET_CLICK == intent.action) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val views = RemoteViews(context.packageName, R.layout.random_numbers_widget)
            val lastUpdate = "Random: " + NumberGenerator.generate(100)
            val appWidgetId = intent.getIntExtra(WIDGET_ID_EXTRA, 0)
            views.setTextViewText(R.id.appwidget_text, lastUpdate)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    private fun getPendingSelfIntent(context: Context, appWidgetId: Int, action: String): PendingIntent {
        val intent = Intent(context, javaClass)
        intent.action = action
        intent.putExtra(WIDGET_ID_EXTRA, appWidgetId)
        return PendingIntent.getBroadcast(context, appWidgetId, intent, 0) // result is "pending intent get all the intent extras with 0 flag"
    }
}



















// end