package com.sokheang.project2322.domain

import java.io.Serializable

data class Profile (
    var profileName: String = "",
    var profileImage: String = "",
    var totalbalance: String = "",
    var income: String = "",
    var outcome: String = "",
    var banner: String = "",
    var friend: ArrayList<Friend> = ArrayList(),
    var transaction: ArrayList<Transaction> = ArrayList(),
) : Serializable

data class Friend(
    var imageUrl: String = "",
    var name: String = ""
) : Serializable

data class Transaction(
    var imageUrl: String = "",
    var name: String = "",
    var date: String = "",
    var amount: String = ""
) : Serializable