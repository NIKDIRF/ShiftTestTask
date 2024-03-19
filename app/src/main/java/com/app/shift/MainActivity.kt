package com.app.shift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.shift.adapter.UserAdapter
import com.app.shift.api.RandomUserApi
import com.app.shift.databinding.ActivityMainBinding
import com.app.shift.db.UserDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userRecycler.layoutManager = LinearLayoutManager(this)
        binding.userRecycler.adapter = adapter

        val db = UserDb.getDb(this)


        CoroutineScope(Dispatchers.IO).launch {
            val users = db.getDao().getAllUsers()
            if (users.isEmpty()) {
                loadUsers()
            } else {
                runOnUiThread {
                    adapter.refresh(users)
                }
            }
        }

        binding.reloadButton.setOnClickListener { loadUsers() }
    }



    private fun loadUsers() {
        val retrofit = Retrofit.Builder().baseUrl("https://randomuser.me").addConverterFactory(GsonConverterFactory.create()).build()
        val randomUserApi = retrofit.create(RandomUserApi::class.java)
        val db = UserDb.getDb(this)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = randomUserApi.getUsers(20)
                if (response.results.isNotEmpty()) {
                    var users = response.results.map { it.toEntity() }
                    db.getDao().deleteAll()
                    db.getDao().insertUsers(users)
                    users = db.getDao().getAllUsers()
                    runOnUiThread {
                        adapter.refresh(users)
                    }
                } else {
                    showToast("Received empty user list")
                }
            } catch (e: Exception) {
                showToast("Error loading user data, check your internet connection")
            }
        }
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}