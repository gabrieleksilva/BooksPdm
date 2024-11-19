package br.edu.scl.ifsp.ads.pdm.bookspdm.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.bookspdm.databinding.ActivityMainBinding
import br.edu.scl.ifsp.ads.pdm.bookspdm.model.Book

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // Data source
    private val bookList: MutableList<Book> = mutableListOf() //inicializando uma lista vazia

    //Adapter
    private val bookAdapter: ArrayAdapter<String> by lazy {
        val bookTitleList: MutableList<String> = mutableListOf()
        bookList.forEach{ book -> bookTitleList.add(book.title) }
        ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            bookTitleList
            )

//        ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            bookList.run{
//                val bookTitleList: MutableList<String> = mutableListOf()
//                this.forEach{ bookTitleList.add(it.title)}
//                bookTitleList
//            }
//        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        fillBookList()
        amb.booksLv.adapter =  bookAdapter
    }
    private fun fillBookList(){
        for (index in 1..50){
            //populando a lista com valores estaticos
            bookList.add(
                Book(
                    "Title $index",
                    "ISBN $index",
                    "Author $index",
                    "Publisher $index",
                    index,
                    index
                )
            )
        }
    }
}