package com.example.appusage

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import bot.box.appusage.datamanager.DataManager
import bot.box.appusage.utils.UsageUtils

/**
 * Implementation of App Widget functionality.
 */
class UsageWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
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
}

internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.usage_widget)
    val u = DataManager.getInstance().getUsedApps(context, 0)

    if (u.size > 0) {
        views.setTextViewText(R.id.name1, u[0].mName)
        views.setTextViewText(R.id.time1, UsageUtils.shortHumanReadableMillis(u[0].mUsageTime))
        views.setTextViewText(R.id.data1, UsageUtils.humanReadableByteCount(u[0].mMobile))
    }

    if (u.size > 1) {
        views.setTextViewText(R.id.name2, u[1].mName)
        views.setTextViewText(R.id.time2, UsageUtils.shortHumanReadableMillis(u[1].mUsageTime))
        views.setTextViewText(R.id.data2, UsageUtils.humanReadableByteCount(u[1].mMobile))
    }

    if (u.size > 2) {
        views.setTextViewText(R.id.name3, u[2].mName)
        views.setTextViewText(R.id.time3, UsageUtils.shortHumanReadableMillis(u[2].mUsageTime))
        views.setTextViewText(R.id.data3, UsageUtils.humanReadableByteCount(u[2].mMobile))
    }
    u.sortByDescending { it.mMobile }

    if (u.size > 0) {
        views.setTextViewText(R.id.name4, u[0].mName)
        views.setTextViewText(R.id.time4, UsageUtils.shortHumanReadableMillis(u[0].mUsageTime))
        views.setTextViewText(R.id.data4, UsageUtils.humanReadableByteCount(u[0].mMobile))
    }

    if (u.size > 1) {
        views.setTextViewText(R.id.name5, u[1].mName)
        views.setTextViewText(R.id.time5, UsageUtils.shortHumanReadableMillis(u[1].mUsageTime))
        views.setTextViewText(R.id.data5, UsageUtils.humanReadableByteCount(u[1].mMobile))
    }

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}