package br.edu.scl.ifsp.ads.pdm.bookspdm.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.scl.ifsp.ads.pdm.bookspdm.R
import br.edu.scl.ifsp.ads.pdm.bookspdm.databinding.ActivityMainBinding
import br.edu.scl.ifsp.ads.pdm.bookspdm.model.Book
import br.edu.scl.ifsp.ads.pdm.bookspdm.model.Constant

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // Data source
    private val bookList: MutableList<Book> = mutableListOf() //inicializando uma lista vazia

    //Adapter
    private val bookAdapter: BookAdapter by lazy {

        BookAdapter(this, bookList)

    }

    private lateinit var barl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        barl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == RESULT_OK){
                val book = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU){
                   result.data?.getParcelableExtra<Book>(Constant.BOOK)
                } else{
                    result.data?.getParcelableExtra(Constant.BOOK, Book::class.java)
                }
                book?.let { receivedBook ->

                    val position = bookList.indexOfFirst { it.isbn == receivedBook.isbn }
                    if(position == -1){
                        bookList.add(receivedBook)
                    }
                    else{
                        bookList[position] = receivedBook
                    }

                    bookAdapter.notifyDataSetChanged() // avisa a view que teve um novo elemento adicionado a lista
                }
            }

        }

        amb.toolbarIn.toolbar.let {
            it.subtitle = getString(R.string.book_list)
            setSupportActionBar(it)
        }

        fillBookList()
        amb.booksLv.adapter =  bookAdapter

        registerForContextMenu(amb.booksLv) // para chamar o menu flutuante
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) = menuInflater.inflate(R.menu.context_menu_main, menu)

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val position = (item.menuInfo as AdapterContextMenuInfo).position
        return when(item.itemId){
            R.id.editBookMi ->{
                //Chamar a tela de edição de livro
                Intent(this, BookActivity::class.java).apply {
                    putExtra(Constant.BOOK, bookList[position])
                    barl.launch(this)
                }
                true
            }R.id.removeBookMi ->{
                //Remover livro da lista
                bookList.removeAt(position)
                bookAdapter.notifyDataSetChanged() //avisando o adapter que foi removido
                true
            }
            else -> {
                false
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.addBookMi -> {
            barl.launch(Intent(this, BookActivity::class.java))

            true
        }
        else -> {
            false
        }
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