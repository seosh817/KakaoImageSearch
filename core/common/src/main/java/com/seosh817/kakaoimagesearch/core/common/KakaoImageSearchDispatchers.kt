package com.seosh817.kakaoimagesearch.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val kakaoImageSearchDispatchers: KakaoImageSearchDispatchers)

enum class KakaoImageSearchDispatchers {
    DEFAULT,
    IO
}
