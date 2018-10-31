package com.lincoln.adam.githubshopifylauncher

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.Period
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.PeriodFormatterBuilder
import java.text.NumberFormat

fun mapModelToViewModel(list: List<RepoModel>): List<RepoViewModel> = list.map { RepoViewModel(it) }

fun getStringFromNumber(repoModel: RepoModel) = NumberFormat.getIntegerInstance().format(repoModel.stargazers_count)!!

fun getTimeSinceCreated(repoModel: RepoModel): String {
    // createdAt: 2008-04-21T18:13:39Z
    val formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ")
    val createdUtc = formatter.parseDateTime(repoModel.created_at)
    val createdLocal = createdUtc.withZone(DateTimeZone.getDefault())
    val nowLocal = DateTime()
    val period = Period(createdLocal, nowLocal)

    val yearsMonths = PeriodFormatterBuilder()
        .appendYears()
        .appendSuffix(" year", " years")
        .appendSeparator(", ")
        .appendMonths()
        .appendSuffix(" month", " months")
        .toFormatter()

    val monthsDays = PeriodFormatterBuilder()
        .appendMonths()
        .appendSuffix(" month", " months")
        .appendSeparator(", ")
        .appendDays()
        .appendSuffix(" day", " days")
        .toFormatter()

    val daysHours = PeriodFormatterBuilder()
        .appendDays()
        .appendSuffix(" day", " days")
        .appendSeparator(", ")
        .appendHours()
        .appendSuffix(" hour", " hours")
        .toFormatter()

    val hoursMinutes = PeriodFormatterBuilder()
        .appendHours()
        .appendSuffix(" hour", " hours")
        .appendSeparator(", ")
        .appendMinutes()
        .appendSuffix(" minute", " minutes")
        .toFormatter()

    val minutesSeconds = PeriodFormatterBuilder()
        .appendMinutes()
        .appendSuffix(" minute", " minutes")
        .appendSeparator(", ")
        .appendSeconds()
        .appendSuffix(" second", " seconds")
        .toFormatter()

    val seconds = PeriodFormatterBuilder()
        .appendSeconds()
        .appendSuffix(" second", " seconds")
        .toFormatter()

    // return period.toString()
    // return PeriodFormat.getDefault().print(period)

    if (period.years > 0)
        return yearsMonths.print(period)

    if (period.months > 0)
        return monthsDays.print(period)

    if (period.days > 0)
        return daysHours.print(period)

    if (period.hours > 0)
        return hoursMinutes.print(period)

    if (period.minutes > 0)
        return minutesSeconds.print(period)

    return seconds.print(period)
}