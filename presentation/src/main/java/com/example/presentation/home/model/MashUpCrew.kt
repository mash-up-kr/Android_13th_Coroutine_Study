package com.example.presentation.home.model

import androidx.annotation.StringRes
import com.example.presentation.R

/**
 * CoroutineStudy
 * @author jaesung
 * @created 2023/04/10
 */
enum class MashUpCrew(
    @StringRes val userName: Int,
    @StringRes val profileImage: Int
)  {
    MASHUP(R.string.mash_up, R.string.mash_up_url),
    JAESUNG(R.string.jaesung, R.string.jaesung_url),
    HYUNKUK(R.string.hyunkuk, R.string.hyunkuk_url),
    JEONGWOO(R.string.jeongwoo, R.string.jeongwoo_url),
    MINJI(R.string.minji, R.string.minji_url),
    DAYEON(R.string.dayeon, R.string.dayeon_url),
    HYUNSOO(R.string.hyunsoo, R.string.hyunsoo_url),
    JAEHUN(R.string.jaehun, R.string.jaehun_url),
    HYEJIN(R.string.hyejin, R.string.hyejin_url)
}