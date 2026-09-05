package io.github.amamic1803.scramble_squares

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

private fun createDataStore(context: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
        migrations = emptyList(),
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        produceFile = { context.preferencesDataStoreFile("app_settings") }
    )

@OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
actual fun createSettings(): FlowSettings {
    if (AppContextHolder.isInitialized()) {
        val context = AppContextHolder.context
        val dataStore = createDataStore(context)
        return DataStoreSettings(dataStore)
    } else {
        // fallback to in-memory storage if context is not initialized
        return MapSettings().toFlowSettings()
    }
}
