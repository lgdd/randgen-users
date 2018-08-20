package com.github.lgdd.liferay.randgen.users.service

import com.liferay.portal.kernel.json.JSONObject
import com.liferay.portal.kernel.util.LocaleUtil
import java.time.ZonedDateTime
import java.util.*

fun JSONObject.getLocale(): Locale {
    val locale = Locale.getAvailableLocales().filter { locale ->
        locale.country == this.getString("nat")
    }
    return if (locale.isEmpty()) LocaleUtil.getDefault() else locale[0]
}

fun JSONObject.getPassword(): String {
    return this.getJSONObject("login").getString("password")
}

fun JSONObject.getEmail(): String {
    return "${this.getScreenName()}@example.com"
}

fun JSONObject.getScreenName(): String {
    return this.getJSONObject("login").getString("username")
}

fun JSONObject.getFirstName(): String {
    return this.getJSONObject("name").getString("first").capitalize()
}

fun JSONObject.getLastName(): String {
    return this.getJSONObject("name").getString("last").capitalize()
}

fun JSONObject.isMale(): Boolean {
    return "male" == this.getString("gender")
}

fun JSONObject.getDateOfBirth(): ZonedDateTime {
    return ZonedDateTime.parse(this.getJSONObject("dob").getString("date"))
}