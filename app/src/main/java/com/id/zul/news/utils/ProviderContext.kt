package com.id.zul.news.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class ProviderContext {
    open val main: CoroutineContext by lazy {
        Dispatchers.Main
    }
}