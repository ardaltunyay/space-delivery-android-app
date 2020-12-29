package com.github.spacedelivery.androidapp.core.exceptions

import java.io.IOException

class ServerException(private val msg: String? = null) : IOException() {
    override val message: String?
        get() = msg
}