package com.exampl.core.interactor

import com.exampl.core.domain.base.Result

abstract class UseCase<out T, in P> where T : Any {

    abstract suspend fun run(params: P): Result<T>

}