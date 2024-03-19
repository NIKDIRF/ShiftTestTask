package com.app.shift.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "firstName")
    val firstName: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "streetNumber")
    val streetNumber: Int,
    @ColumnInfo(name = "streetName")
    val streetName: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "state")
    val state: String,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "postcode")
    val postcode: String,
    @ColumnInfo(name = "latitude")
    val latitude: String,
    @ColumnInfo(name = "longitude")
    val longitude: String,
    @ColumnInfo(name = "timezoneOffset")
    val timezoneOffset: String,
    @ColumnInfo(name = "timezoneDescription")
    val timezoneDescription: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "username")
    val username: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "salt")
    val salt: String,
    @ColumnInfo(name = "md5")
    val md5: String,
    @ColumnInfo(name = "sha1")
    val sha1: String,
    @ColumnInfo(name = "sha256")
    val sha256: String,
    @ColumnInfo(name = "dobDate")
    val dobDate: String,
    @ColumnInfo(name = "dobAge")
    val dobAge: Int,
    @ColumnInfo(name = "registeredDate")
    val registeredDate: String,
    @ColumnInfo(name = "registeredAge")
    val registeredAge: Int,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "cell")
    val cell: String,
    @ColumnInfo(name = "userIdName")
    val userIdName: String,
    @ColumnInfo(name = "userIdValue")
    val userIdValue: String?,
    @ColumnInfo(name = "pictureLarge")
    val pictureLarge: String,
    @ColumnInfo(name = "pictureMedium")
    val pictureMedium: String,
    @ColumnInfo(name = "pictureThumbnail")
    val pictureThumbnail: String,
    @ColumnInfo(name = "nat")
    val nat: String
)