package com.sokheang.project2322.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sokheang.project2322.FriendsAdapter
import com.sokheang.project2322.R
import com.sokheang.project2322.databinding.ActivityMainBinding
import com.sokheang.project2322.domain.Profile
import com.sokheang.project2322.viewmodel.ProfileViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var userProfile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPopular()

        binding.layoutDetail.setOnClickListener {
            val intent = Intent(this@MainActivity, OverviewActivity::class.java)

            intent.putExtra("profile_data", userProfile)
            startActivity(intent)
        }
    }

    private fun initPopular() {
        binding.progressBar.visibility = View.VISIBLE
        binding.scrollView.visibility = View.GONE
        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        viewModel.profileLiveData.observe(this) { profile ->
            if(profile != null) {
                userProfile = profile
                binding.scrollView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.tvName.text = profile.profileName
                binding.tvTotalBalanceValue.text = profile.totalbalance
                binding.tvIncomeValue.text = profile.income
                binding.tvOutcomeValue.text = profile.outcome

                Glide.with(this@MainActivity)
                    .load(profile.profileImage)
                    .into(binding.ivProfile)

                Glide.with(this@MainActivity)
                    .load(profile.banner)
                    .into(binding.ivBanner)

                binding.friendsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding.friendsList.adapter = FriendsAdapter(profile.friend)
            }
        }
    }
}