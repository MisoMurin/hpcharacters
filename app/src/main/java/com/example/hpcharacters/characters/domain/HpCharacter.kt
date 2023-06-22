package com.example.hpcharacters.characters.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HpCharacter(
    val name: String,
    val actor: String,
    val image: String,
): Parcelable
