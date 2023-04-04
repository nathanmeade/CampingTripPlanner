package com.camp.campingtripplanner

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun getWeatherApiKey() : String {
        return BuildConfig.WEATHER_API_API_KEY
    }

//    @Provides
//    fun getWeatherApiKey() : String {
//        return BuildConfig.OPEN_WEATHER_API_KEY
//    }

//    @Provides
//    @Singleton
//    fun provideOpenWeatherApi(@ApplicationContext context: Context): OpenWeatherApi {
//        return Retrofit.Builder()
//            .baseUrl(context.getString(R.string.open_weather_base_url))
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(OpenWeatherApi::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideOpenWeatherRepository(openWeatherApi: OpenWeatherApi): WeatherRepository {
//        return OpenWeatherRepository(openWeatherApi)
//    }

    @Provides
    @Singleton
    fun provideWeatherApiApi(@ApplicationContext context: Context): WeatherApiApi {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.weather_api_base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherApiRepository(weatherApiApi: WeatherApiApi): WeatherRepository {
        return WeatherApiRepository(weatherApiApi)
    }

    @Provides
    @Singleton
    fun provideTripRepository(tripDao: CampingTripDao): CampingTripRepository {
        return RoomCampingTripRepository(tripDao)
    }

    @Provides
    @Singleton
    fun provideTripDao(database: CampingTripDatabase): CampingTripDao {
        return database.campingTripDao()
    }

    @Provides
    @Singleton
    fun provideTripDatabase(@ApplicationContext context: Context): CampingTripDatabase {
        return CampingTripDatabase.getInstance(context)
    }

}
