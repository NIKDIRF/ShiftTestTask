package com.app.shift

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.shift.databinding.ActivityUserProfileBinding
import com.app.shift.db.UserDb
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.closeIcon.setOnClickListener { finish() }
        
        val db = UserDb.getDb(this)
        CoroutineScope(Dispatchers.IO).launch {
            val userId = intent.getIntExtra("user_id", 0)
            val user = db.getDao().getUserById(userId)

            runOnUiThread {
                Picasso.get().load(user?.pictureLarge).into(binding.avatar)
                var text = "${user?.title} ${user?.firstName} ${user?.lastName}"
                binding.fio.text = text
                binding.emailText.text = user?.email
                binding.emailText.setOnClickListener {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:${user?.email}")
                    }
                    startActivity(intent)
                }

                binding.callText.text = user?.phone
                binding.callText.setOnClickListener {
                    val intent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:${user?.phone}")
                    }
                    startActivity(intent)
                }

                val coordinates = user?.latitude + "," +  user?.longitude
                binding.coordinatesText.text = coordinates
                binding.coordinatesText.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("geo:0,0?q=$coordinates")
                    }
                    startActivity(intent)
                }

                text = "Gender: " + user?.gender
                binding.genderText.text = text
                text = "    Title: " + user?.title
                binding.nameTitleText.text = text
                text = "    First: " + user?.firstName
                binding.nameFirstText.text = text
                text = "     Last: " + user?.lastName
                binding.nameLastText.text = text
                text = "     Country: " + user?.country
                binding.locationCountryText.text = text
                text = "     State: " + user?.state
                binding.locationStateText.text = text
                text = "     City: " + user?.city
                binding.locationCityText.text = text
                text = "     Street: " + user?.streetName + " " + user?.streetNumber
                binding.locationStreetText.text = text
                text = "     Postcode: " + user?.postcode
                binding.locationPostcodeText.text = text
                text = "              Latitude: " + user?.latitude
                binding.locationCoordinatesLatitudeText.text = text
                text = "              Longitude: " + user?.longitude
                binding.locationCoordinatesLongitudeText.text = text
                text = "              Description: " + user?.timezoneDescription
                binding.timezoneDescriptionText.text = text
                text = "              Offset:" + user?.timezoneOffset
                binding.timezoneOffsetText.text = text
                text = "Email: " + user?.email
                binding.emailSliderText.text = text
                text = "Date of birth: " + user?.dobDate?.take(10)
                binding.dobDateText.text = text
                text = "Age: " + user?.dobAge
                binding.dobAgeText.text = text
                text = "     Date: " + user?.registeredDate?.take(10)
                binding.registeredDateText.text = text
                text = "     Age: " + user?.registeredAge
                binding.registeredAgeText.text = text
                text = "Phone: " + user?.phone
                binding.phoneText.text = text
                text = "Cell: " + user?.cell
                binding.cellText.text = text
            }
        }

    }
}