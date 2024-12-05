package br.edu.scl.ifsp.ads.pdm.bookspdm.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.bookspdm.databinding.ActivityBookBinding
import br.edu.scl.ifsp.ads.pdm.bookspdm.model.Book
import br.edu.scl.ifsp.ads.pdm.bookspdm.model.Constant

class BookActivity : AppCompatActivity() {

    private val abb: ActivityBookBinding by lazy {
        ActivityBookBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(abb.root)
        // se for falso ou vazio ele vai para o modo visualização e nao modo edicao
        val viewMode = intent.getBooleanExtra(Constant.VIEW_MODE, false)

        val receivedBook = intent.getParcelableExtra<Book>(Constant.BOOK)
        receivedBook?.let { book ->
            with(abb) {
                with(book) {
                    //modo edicao
                    titleEt.setText(title)
                    isbnEt.setText(isbn)
                    isbnEt.isEnabled = false
                    firstAuthorEt.setText(firstAuthor)
                    publisherEt.setText(publisher)
                    editionEt.setText(edition.toString())
                    pagesEt.setText(pages.toString())

                    //modo visualização
                    titleEt.isEnabled = !viewMode
                    firstAuthorEt.isEnabled = !viewMode
                    publisherEt.isEnabled = !viewMode
                    editionEt.isEnabled = !viewMode
                    pagesEt.isEnabled = !viewMode
                    saveBt.visibility = if (viewMode) GONE else View.VISIBLE

                }
            }

        }

        abb.toolbarIn.toolbar.let {
            it.subtitle =
                if (receivedBook == null)
                    "New book"
                else
                    if (viewMode)
                        "Book details"
                    else
                        "Edit book"
            setSupportActionBar(it)
        }

        abb.run {
            saveBt.setOnClickListener {
                Book(
                    titleEt.text.toString(),
                    isbnEt.text.toString(),
                    firstAuthorEt.text.toString(),
                    publisherEt.text.toString(),
                    editionEt.text.toString().toInt(),
                    pagesEt.text.toString().toInt()
                ).let { book ->
                    Intent().apply {
                        putExtra(Constant.BOOK, book)
                        setResult(RESULT_OK, this)
                        finish()
                    }
                }
            }
        }
    }
}