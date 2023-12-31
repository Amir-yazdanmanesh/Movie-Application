package com.yazdanmanesh.movieapp.common.extension

import com.google.gson.Gson
import com.yazdanmanesh.movieapp.R
import com.yazdanmanesh.movieapp.common.utils.UiText
import com.yazdanmanesh.movieapp.data.remote.models.ErrorDto
import com.yazdanmanesh.movieapp.data.remote.models.toErrorModel
import retrofit2.HttpException

val gson = Gson()

@Synchronized
fun HttpException.handleError(): UiText {
    val errorString = this.response()?.errorBody()?.string()
    errorString?.let {
        val errorModel = gson.fromJson(errorString, ErrorDto::class.java)?.toErrorModel()
        if (errorModel?.error != null) {
            return UiText.DynamicString(errorModel.error)
        } else {
            return UiText.StringResource(R.string.unexpectedError)
        }
    }
    return this.localizedMessage?.let { UiText.DynamicString(it) }
        ?: UiText.StringResource(R.string.unexpectedError)
}
