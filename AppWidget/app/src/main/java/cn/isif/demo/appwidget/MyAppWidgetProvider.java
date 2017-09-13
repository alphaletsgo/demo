package cn.isif.demo.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by zhy_h on 2017/9/13.
 */

public class MyAppWidgetProvider extends AppWidgetProvider {
    private static final String TAG = "MyAppWidgetProvider";
    private static final String ACTION = "cn.isif.demo.appwidget.IMG";

    //当appwidget删除的时候会调用这个函数
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        Log.d(TAG,"onDeleted");
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        Log.d(TAG,"onDisabled");
        super.onDisabled(context);
    }

    //当appwidget被创建的时候调用
    @Override
    public void onEnabled(Context context) {
        Log.d(TAG,"onEnabled");
        super.onEnabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"onReceive");
        if (intent.getAction().equals(ACTION)){
            Toast.makeText(context,"action:"+ACTION,Toast.LENGTH_LONG).show();
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(TAG,"onUpdate");
        int length = appWidgetIds.length;
        for (int i = 0; i < length; i++) {
            onWidgetUpdate(context,appWidgetManager,appWidgetIds[i]);
        }
    }

    public void onWidgetUpdate(Context context,AppWidgetManager appWidgetManager,int appWidgetId){
        Log.d(TAG,"onWidgetUpdate");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget);

        Intent intent = new Intent(ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,0);
        remoteViews.setOnClickPendingIntent(R.id.img,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
    }
}
