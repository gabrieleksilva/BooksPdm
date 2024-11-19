package br.edu.scl.ifsp.ads.pdm.bookspdm.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val title: String,
    val isbn: String,
    val firstAuthor: String,
    val publisher: String,
    val edition: Int,
    val pages: Int
): Parcelable