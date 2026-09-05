package io.github.amamic1803.scramble_squares

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlin.reflect.KProperty

@OptIn(ExperimentalSettingsApi::class)
expect fun createSettings(): FlowSettings

@OptIn(ExperimentalSettingsApi::class)
object Storage {
    private val value: FlowSettings = createSettings()

    operator fun getValue(thisRef: Any?, property: KProperty<*>): FlowSettings {
        return value
    }
}
