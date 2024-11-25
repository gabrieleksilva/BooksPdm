package br.edu.scl.ifsp.ads.pdm.bookspdm.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.scl.ifsp.ads.pdm.bookspdm.R
import br.edu.scl.ifsp.ads.pdm.bookspdm.databinding.ActivityBookBinding

class BookActivity : AppCompatActivity() {

    private val abb: ActivityBookBinding by lazy{
        ActivityBookBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(abb.root)
    }
}