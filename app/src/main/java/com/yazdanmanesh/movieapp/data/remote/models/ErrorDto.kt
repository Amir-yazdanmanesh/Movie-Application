package com.yazdanmanesh.movieapp.data.remote.models

import com.yazdanmanesh.movieapp.domain.models.ErrorModel

data class ErrorDto(val error: String?)

fun ErrorDto.toErrorModel(): ErrorModel = ErrorModel(error = error)
