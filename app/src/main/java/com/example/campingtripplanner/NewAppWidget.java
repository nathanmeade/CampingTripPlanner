package com.example.campingtripplanner;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.room.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private AppDatabase db;
    private Trip trip;

    private Context contextVariable;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, List<Trip> list) {


        CharSequence widgetText = context.getString(R.string.appwidget_text);
        widgetText = NewAppWidget.widgetTextCreator(list);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }



    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
/*        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }*/
        new AsyncTask<Context, Void, List<Trip>>(){
            @Override
            protected List<Trip> doInBackground(Context... context) {

                List<Trip> list=null;
                AppDatabase db = Room.databaseBuilder(context[0],
                        AppDatabase.class, "database-name").allowMainThreadQueries().build();
                list = db.tripDao().getAll();


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

        }.execute(context);
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
/*        db = Room.databaseBuilder(this,
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        List<Trip> trips = db.tripDao().getAll();*/
        for (Trip trip : trips){
            /*trip.arrival*/
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                d = sdf.parse(trip.arrival);
            } catch (ParseException ex) {
                Log.v("Exception", ex.getLocalizedMessage());
            }
            if (d.after(date)){
                Log.d("nathanTest", "after " + trip.arrival + " " + d.compareTo(date));
                tripsSaved = true;
                /*approachingTrip = true;*/
                if (diffInMillies2 == 0){
                    diffInMillies2 = Math.abs(d.getTime() - date.getTime());
                }
                else if ((Math.abs(d.getTime() - date.getTime())) < diffInMillies2){
                    diffInMillies2 = Math.abs(d.getTime() - date.getTime());
                }
            }
            else {
                Log.d("nathanTest", "before" + trip.arrival);
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
            //no trips saved displayed
            /*Log.d("nathanTest", "no trips saved");*/
            return "No Trips Saved";
        }
        else if (!approachingTrip){
            //"it's been " (positive difference in days between closest camping trip) " days since your last camping trip!
            //^shouldn't this actually be for departure?
            /*Log.d("nathanTest", "no approaching trips" + (diffInMillies / (1000*60*60*24)));*/
            return "No approaching trips "+ (diffInMillies / (1000*60*60*24));
        }
        else {
            //"You have " (days until closest camping trip) " until your next camping trip!
            /*Log.d("nathanTest", "approaching trip" + diffInMillies2);*/
            /*Log.d("nathanTest", "approaching trip" + (diffInMillies2 / (1000*60*60*24)));*/
            return "Approaching trip " + (diffInMillies2 / (1000*60*60*24));
        }
    }


}

