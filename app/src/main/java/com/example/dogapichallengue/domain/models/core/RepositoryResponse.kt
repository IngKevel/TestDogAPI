package com.example.dogapichallengue.domain.models.core

/**
 * Created by Kevel on 3/23/2023.
 */
sealed class RepositoryResponse<out T> {
    data class Success<T>(val data: T): RepositoryResponse<T>()
    data class Error(val code: Int, val message: String, val throwable: Exception? = null): RepositoryResponse<Nothing>()
}
