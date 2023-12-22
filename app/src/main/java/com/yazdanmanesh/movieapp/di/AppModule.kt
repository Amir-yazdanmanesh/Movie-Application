package com.yazdanmanesh.movieapp.di

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.Gson
import com.yazdanmanesh.movieapp.BuildConfig
import com.yazdanmanesh.movieapp.data.local.DatabaseConverters
import com.yazdanmanesh.movieapp.data.local.MovieDao
import com.yazdanmanesh.movieapp.data.local.MovieDatabase
import com.yazdanmanesh.movieapp.data.utils.GsonParser
import com.yazdanmanesh.movieapp.domain.mappers.MovieMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(
            BuildConfig.APPLICATION_ID,
            Activity.MODE_PRIVATE
        )

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application, gson: Gson): MovieDatabase {
        return Room.databaseBuilder(app, MovieDatabase::class.java, "movie_db")
            .addTypeConverter(DatabaseConverters(GsonParser(gson)))
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: MovieDatabase): MovieDao = db.dao

    @Provides
    @Singleton
    fun provideMovieMapper(): MovieMapper = MovieMapper()
}
