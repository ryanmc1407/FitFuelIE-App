package com.fitfuelie.app.di

import android.content.Context
import androidx.room.Room
import com.fitfuelie.app.data.FitFuelDatabase
import com.fitfuelie.app.data.dao.*
import com.fitfuelie.app.data.repository.MealRepository
import com.fitfuelie.app.data.repository.UserProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FitFuelDatabase {
        return Room.databaseBuilder(
            context,
            FitFuelDatabase::class.java,
            FitFuelDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideMealDao(database: FitFuelDatabase): MealDao = database.mealDao()

    @Provides
    fun provideTrainingSessionDao(database: FitFuelDatabase): TrainingSessionDao = database.trainingSessionDao()

    @Provides
    fun provideGroceryItemDao(database: FitFuelDatabase): GroceryItemDao = database.groceryItemDao()

    @Provides
    fun provideUserProfileDao(database: FitFuelDatabase): UserProfileDao = database.userProfileDao()

    @Provides
    @Singleton
    fun provideMealRepository(mealDao: MealDao): MealRepository = MealRepository(mealDao)

    @Provides
    @Singleton
    fun provideUserProfileRepository(userProfileDao: UserProfileDao): UserProfileRepository =
        UserProfileRepository(userProfileDao)
}
