package com.wajahatkarim.movies.swvl.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = MovieModel.TABLE_NAME)
data class MovieModel(
    @PrimaryKey(autoGenerate = true) val movieId: Long = 0,

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("year")
    @Expose
    @ColumnInfo(name = "year")
    val year: Int,

    @SerializedName("rating")
    @Expose
    val rating: Int,

    @SerializedName("cast")
    @Expose
    @ColumnInfo(name = "cast")
    var cast: List<String>? = null,

    @SerializedName("genres")
    @Expose
    @ColumnInfo(name = "genres")
    var genres: List<String>? = null
): Parcelable {

    companion object {
        const val TABLE_NAME = "MoviesTable"
    }
}