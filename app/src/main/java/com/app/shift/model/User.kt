package com.app.shift.model

import com.app.shift.db.UserEntity

data class User(
    val pkId: Int?,
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: Dob,
    val registered: Registered,
    val phone: String,
    val cell: String,
    val id: UserId,
    val picture: Picture,
    val nat: String
) {
    fun toEntity(): UserEntity {
        return UserEntity(
            gender = this.gender,
            title = this.name.title,
            firstName = this.name.first,
            lastName = this.name.last,
            streetNumber = this.location.street.number,
            streetName = this.location.street.name,
            city = this.location.city,
            state = this.location.state,
            country = this.location.country,
            postcode = this.location.postcode,
            latitude = this.location.coordinates.latitude,
            longitude = this.location.coordinates.longitude,
            timezoneOffset = this.location.timezone.offset,
            timezoneDescription = this.location.timezone.description,
            email = this.email,
            uuid = this.login.uuid,
            username = this.login.username,
            password = this.login.password,
            salt = this.login.salt,
            md5 = this.login.md5,
            sha1 = this.login.sha1,
            sha256 = this.login.sha256,
            dobDate = this.dob.date,
            dobAge = this.dob.age,
            registeredDate = this.registered.date,
            registeredAge = this.registered.age,
            phone = this.phone,
            cell = this.cell,
            userIdName = this.id.name,
            userIdValue = this.id.value,
            pictureLarge = this.picture.large,
            pictureMedium = this.picture.medium,
            pictureThumbnail = this.picture.thumbnail,
            nat = this.nat
        )
    }
}
