package com.camp.campingtripplanner;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class NewAppWidget extends AppWidgetProvider {

    private AppDatabase db;
    private Trip trip;

    private Context contextVariable;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, List<Trip> list) {
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        widgetText = NewAppWidget.widgetTextCreator(list);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        new GetTripsTask(appWidgetManager, appWidgetIds, context).execute(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static String widgetTextCreator(List<Trip> trips){
        boolean approachingTrip;
        boolean tripsSaved;
        long diffInMillies;
        long diffInMillies2;
        diffInMillies = 0;
        diffInMillies2 = 0;
        approachingTrip = false;
        tripsSaved = false;
        Date date = Calendar.getInstance().getTime();
        Date d = new Date();
        Date closestDate = new Date();
        for (Trip trip : trips){
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                d = sdf.parse(trip.arrival);
            } catch (ParseException ex) {
                Log.v("Exception", ex.getLocalizedMessage());
            }
            if (d.after(date)){
                tripsSaved = true;
                approachingTrip = true;
                if (diffInMillies2 == 0){
                    diffInMillies2 = Math.abs(d.getTime() - date.getTime());
                    closestDate = d;
                }
                else if ((Math.abs(d.getTime() - date.getTime())) < diffInMillies2){
                    diffInMillies2 = Math.abs(d.getTime() - date.getTime());
                    closestDate = d;
                }
            }
            else {
                tripsSaved = true;
                if (diffInMillies == 0){
                    diffInMillies = Math.abs(date.getTime() - d.getTime());
                }
                else if ((Math.abs(date.getTime() - d.getTime())) < diffInMillies){
                    diffInMillies = Math.abs(date.getTime() - d.getTime());
                }
            }
        }
        if (!tripsSaved){
            return "No upcoming camping trips";
        }
        else if (!approachingTrip){
            return "No upcoming camping trips";
        }
        else {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            return "Your next camping trip is on:\n" + dateFormat.format(closestDate);
        }
    }

    public class GetTripsTask extends AsyncTask<Context, Void, List<Trip>>{
        AppWidgetManager appWidgetManager;
        int[] appWidgetIds;
        Context context;
        GetTripsTask(AppWidgetManager appWidgetManager, int[] appWidgetIds, Context context){
            this.appWidgetManager = appWidgetManager;
            this.appWidgetIds = appWidgetIds;
            this.context = context;
        }
        @Override
        protected List<Trip> doInBackground(Context... context) {
            List<Trip> list = null;
            AppDatabase db = AppDatabase.getInstance(context[0]);
            list = db.tripDao().widgetGetAll();
            return list;
        }

        @Override
        protected void onPostExecute(List<Trip> list) {
            super.onPostExecute(list);
            if(list != null){
                final Random random=new Random();
                for (int appWidgetId : appWidgetIds) {
                    updateAppWidget(context, appWidgetManager, appWidgetId, list);
                }
            }
        }
    }


}

