package io.github.amamic1803.scramble_squares

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.StorageSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import com.russhwolf.settings.observable.makeObservable
import kotlinx.browser.localStorage

@OptIn(ExperimentalSettingsApi::class)
actual fun createSettings(): FlowSettings {
    val settings = StorageSettings(localStorage)
    return settings.makeObservable().toFlowSettings()
}