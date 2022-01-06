package com.sadio.Miniproject.apis.jokes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/** Object use for room */
@Entity(tableName = "jokes")
data class JokesRoom(
    @ColumnInfo(name = "joke_category")
    val category: String,

    @ColumnInfo(name = "joke_setup")
    val setup: String,

    @ColumnInfo(name = "joke_delivery")
    val delivery: String,

    @ColumnInfo(name = "joke_api")
    val api: String,

    @ColumnInfo(name = "joke_timestamp")
    val timestamp: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

/** Object use for Ui */
data class JokesUi(
    val category: String,
    val setup: String,
    val delivery: String,
    val api: String,
    val timestamp: Long
)

/** Object use for retrofit */
data class JokesRetrofit(
    @Expose
    @SerializedName("category")
    val category: String,

    @Expose
    @SerializedName("setup")
    val setup: String,

    @Expose
    @SerializedName("delivery")
    val delivery: String,

    @Expose
    @SerializedName("api")
    val api: String,

    @Expose
    @SerializedName("timestamp")
    val timestamp: Long
)
