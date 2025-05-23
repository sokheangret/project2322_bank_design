package com.sokheang.project2322.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sokheang.project2322.domain.Profile

class ProfileRepository {
    private val profileRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("profile")
    private val profileLiveData: MutableLiveData<Profile> = MutableLiveData()

    fun getProfileLiveData(): MutableLiveData<Profile> {
        profileRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val profile = snapshot.getValue(Profile::class.java)
                profileLiveData.value = profile
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return profileLiveData
    }
}