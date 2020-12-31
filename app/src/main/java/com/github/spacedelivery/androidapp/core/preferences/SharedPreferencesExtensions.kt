package com.github.spacedelivery.androidapp.core.preferences

import android.content.SharedPreferences
import com.google.gson.Gson

private inline fun SharedPreferences.editor(crossinline operation: SharedPreferences.Editor.() -> Unit) {
    edit().apply { operation() }.apply()
}

operator fun <T : Any> SharedPreferences.set(key: String, value: T?) {
    when (value) {
        is String -> editor { putString(key, value) }
        is Boolean -> editor { putBoolean(key, value) }
        is Int -> editor { putInt(key, value) }
        is Long -> editor { putLong(key, value) }
        is Float -> editor { putFloat(key, value) }
        is T -> editor { putString(key, Gson().toJson(value)) }
        else -> {
            if (value == null)
                editor { remove(key) }
            else
                throw IllegalArgumentException("$value type not found")
        }
    }
}

inline operator fun <reified T : Any> SharedPreferences.get(key: String, defValue: T? = null): T? {
    return when (T::class) {
        String::class -> getString(key, defValue as? String) as T?
        Boolean::class -> getBoolean(key, (defValue as? Boolean) ?: false) as T?
        Int::class -> getInt(key, (defValue as? Int) ?: 0) as T?
        Long::class -> getLong(key, (defValue as? Long) ?: 0L) as T?
        Float::class -> getFloat(key, (defValue as? Float) ?: 0F) as T?
        T::class -> try {
            Gson().fromJson(getString(key, null), T::class.java)
        } catch (ex: Exception) {
            null
        }
        else -> throw IllegalArgumentException("$key not found")
    }
}