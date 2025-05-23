package com.sokheang.project2322.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sokheang.project2322.domain.Profile
import com.sokheang.project2322.repository.ProfileRepository

class ProfileViewModel : ViewModel() {
    private  val repository: ProfileRepository = ProfileRepository()
    val profileLiveData: LiveData<Profile> = repository.getProfileLiveData()
}