package juniar.nicolas.androidskeletoncore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import juniar.nicolas.androidskeletoncore.GenericDataStoreHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create {
            context.dataStoreFile("skeleton_preferences_prefs")
        }

    @Provides
    @Singleton
    fun provideGenericDataStoreHelper(dataStore: DataStore<Preferences>):GenericDataStoreHelper =
        GenericDataStoreHelper(dataStore)
}