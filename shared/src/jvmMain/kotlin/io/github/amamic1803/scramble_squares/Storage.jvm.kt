package io.github.amamic1803.scramble_squares

import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.io.File
import kotlin.collections.emptyList

@OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
actual fun createSettings(): FlowSettings {
    val dataStore = createDataStore()
    return DataStoreSettings(dataStore)
}

private fun appDataDir(): File {
    val os = System.getProperty("os.name").lowercase()
    val userHome = System.getProperty("user.home")
    val dir = when {
        os.contains("win") -> File(System.getenv("APPDATA") ?: userHome, "Scramble-Squares")
        os.contains("mac") -> File(userHome, "Library/Application Support/Scramble-Squares")
        else -> File(userHome, ".local/share/Scramble-Squares") // linux
    }
    if (!dir.exists()) dir.mkdirs()
    return dir
}

private fun createDataStore(): DataStore<Preferences> {
    val file = File(appDataDir(), "app_settings.preferences_pb")
    return PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
        migrations = emptyList(),
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        produceFile = { file }
    )
}
