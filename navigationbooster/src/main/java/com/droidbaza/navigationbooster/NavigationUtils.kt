package com.droidbaza.navigationbooster

import android.os.Binder
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import java.io.Serializable

object NavigationUtils {

    fun NavHostController.navigate(
        route: String,
        args: Array<out Pair<String, Any>> = emptyArray(),
        restoreState: Boolean = true,
        popUpTo: String? = null,
        launchSingleTop: Boolean = true,
        popUpInclusive: Boolean = false,
        extras: Navigator.Extras? = null,
    ) {
        val navArgs = Bundle()
        args.forEach {
            navArgs.set(it)
        }
        val options = NavOptions
            .Builder()
            .setLaunchSingleTop(launchSingleTop)
            .setRestoreState(restoreState)
            .setPopUpTo(popUpTo, popUpInclusive)
            .build()

        val resId = NavDestination.createRoute(route).hashCode()
        navigate(resId, navArgs, options, extras)
    }

    fun NavHostController.navigate(
        route: String,
        args: Array<out Pair<String, Any>> = emptyArray(),
        options: NavOptions? = null,
        extras: Navigator.Extras? = null,
    ) {
        val navArgs = Bundle()
        args.forEach {
            navArgs.set(it)
        }
        val resId = NavDestination.createRoute(route).hashCode()
        navigate(resId, navArgs, options, extras)
    }

    fun NavController.isDestinationExist(route: String) =
        backQueue.findLast {
            it.destination.route == route
        } != null

    inline fun <reified T : Any> Bundle.set(it: Pair<String, T>?) {
        val key = it?.first ?: return
        when (val value = it.second) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Short -> putShort(key, value)
            is Long -> putLong(key, value)
            is Byte -> putByte(key, value)
            is Boolean -> putBoolean(key, value)
            is ByteArray -> putByteArray(key, value)
            is Char -> putChar(key, value)
            is CharArray -> putCharArray(key, value)
            is CharSequence -> putCharSequence(key, value)
            is Float -> putFloat(key, value)
            is Bundle -> putBundle(key, value)
            is Serializable -> putSerializable(key, value)
            is Parcelable -> putParcelable(key, value)
            is Binder -> putBinder(key, value)
            is List<*> -> {
                val item = value.firstOrNull() ?: return
                when (item) {
                    is Boolean -> putBooleanArray(
                        key,
                        value.filterIsInstance<Boolean>().toBooleanArray()
                    )

                    is Parcelable -> putParcelableArray(
                        key,
                        value.filterIsInstance<Parcelable>().toTypedArray()
                    )

                    is Int -> putIntArray(key, value.filterIsInstance<Int>().toIntArray())
                    is Short -> putShortArray(key, value.filterIsInstance<Short>().toShortArray())
                    is Float -> putFloatArray(key, value.filterIsInstance<Float>().toFloatArray())
                    is Double -> putDoubleArray(
                        key,
                        value.filterIsInstance<Double>().toDoubleArray()
                    )

                    is Long -> putLongArray(key, value.filterIsInstance<Long>().toLongArray())

                    is String -> value.filterIsInstance<String>().let {
                        val result = arrayOfNulls<String>(it.size)
                        it.forEachIndexed { index, element ->
                            result[index] = element
                        }
                        putStringArray(key, result)
                    }

                    is CharSequence -> value.filterIsInstance<CharSequence>().let {
                        val result = arrayOfNulls<CharSequence>(it.size)
                        it.forEachIndexed { index, element ->
                            result[index] = element
                        }
                        putCharSequenceArray(key, result)
                    }

                    else -> throw IllegalStateException("Type ${item.javaClass.canonicalName} is not supported")
                }
            }

            else -> throw IllegalStateException("Type ${value.javaClass.canonicalName} is not supported")
        }
    }

    @Suppress("DEPRECATION")
    inline fun <reified T : Any?> NavBackStackEntry.get(
        key: String,
        defaultValue: T? = null,
        isBackArgs: Boolean = false,
        clearBackArgsAfterGet: Boolean = false
    ): T? {
        val result: T?
        try {
            if (isBackArgs) {
                result = savedStateHandle.get<T>(key)
                if (clearBackArgsAfterGet) {
                    savedStateHandle.remove<T>(key)
                }
            } else {
                result = arguments?.get(key) as T?
            }
        } catch (ex: Exception) {
            throw ex
        }
        return result ?: defaultValue
    }

    @Suppress("DEPRECATION")
    inline fun <reified T : Any> NavBackStackEntry.getNonNull(
        key: String,
        isBackArgs: Boolean = false
    ): T {
        val result: T?
        try {
            if (isBackArgs) {
                result = savedStateHandle.get<T>(key)
                savedStateHandle.remove<T>(key)
            } else {
                result = arguments?.get(key) as T?
            }
        } catch (ex: Exception) {
            throw ex
        }
        return checkNotNull(result)
    }

    inline fun <reified T : Any> NavController.popBackStack(
        args: Pair<String, T?>? = null,
        route: String? = null,
        inclusive: Boolean = false,
        saveState: Boolean = false,
    ) {
        if (args != null) {
            if (route != null) {
                val destinationId = NavDestination.createRoute(route).hashCode()
                backQueue.findLast {
                    it.destination.route == route
                }?.savedStateHandle?.set(args.first, args.second)
                popBackStack(
                    destinationId = destinationId,
                    inclusive = inclusive,
                    saveState = saveState
                )
            } else {
                previousBackStackEntry?.savedStateHandle?.set(args.first, args.second)
                popBackStack()
            }
        } else popBackStack()
    }


    inline fun <reified T : Any> SavedStateHandle.getNonNull(key: String,defaultValue:T?=null): T {
        val result = get<T>(key)
        return checkNotNull(result?:defaultValue)
    }

    inline fun <reified T : Any> SavedStateHandle.get(key: String,defaultValue:T?=null): T? {
        return get<T>(key) ?:defaultValue
    }
}