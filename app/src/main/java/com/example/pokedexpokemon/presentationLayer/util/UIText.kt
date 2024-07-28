package com.example.pokedexpokemon.presentationLayer.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


sealed class UIText {
    data class DynamicString(
        val value: String
    ) : UIText()

    data class StringResource(
        @StringRes val id: Int, val args: List<Any> = emptyList()
    ) : UIText() {
        fun getID(): Int {
            return this.id
        }
    }


    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(id, *args.toTypedArray())
        }
    }


    fun toString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args.toTypedArray())
        }
    }

    @Composable
    fun isBlank(): Boolean {
        return when (this) {
            is DynamicString -> {
                this.value.isBlank()
            }

            is StringResource -> {
                stringResource(id, *args.toTypedArray()).isBlank()
            }
        }
    }

    @Composable
    fun isNotBlank(): Boolean {
        return when (this) {
            is DynamicString -> {
                this.value.isNotBlank()
            }

            is StringResource -> {
                stringResource(id, *args.toTypedArray()).isNotBlank()
            }
        }
    }

    @Composable
    fun isEmpty(): Boolean {
        return when (this) {
            is DynamicString -> {
                this.value.isEmpty()
            }

            is StringResource -> {
                stringResource(id, *args.toTypedArray()).isEmpty()
            }
        }
    }

    @Composable
    fun isNotEmpty(): Boolean {
        return when (this) {
            is DynamicString -> {
                this.value.isNotEmpty()
            }

            is StringResource -> {
                stringResource(id, *args.toTypedArray()).isNotEmpty()
            }
        }
    }
}