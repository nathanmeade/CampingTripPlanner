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
    @Singleton
    fun provideOpenWeatherApi(@ApplicationContext context: Context): OpenWeatherApi {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.open_weather_base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOpenWeatherRepository(openWeatherApi: OpenWeatherApi): OpenWeatherRepository {
        return OpenWeatherRepositoryImpl(openWeatherApi)
    }

    @Provides
    @Singleton
    fun provideTripRepository(tripDao: CampingTripDao): CampingTripRepository {
        return CampingTripRepositoryImpl(tripDao)
    }

    @Provides
    @Singleton
    fun provideTripDatabase(@ApplicationContext context: Context): CampingTripDatabase {
        return CampingTripDatabase.getInstance(context)
    }

}
