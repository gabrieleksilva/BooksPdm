package br.edu.scl.ifsp.ads.pdm.bookspdm.ui

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.scl.ifsp.ads.pdm.bookspdm.R
import br.edu.scl.ifsp.ads.pdm.bookspdm.databinding.TileBookBinding
import br.edu.scl.ifsp.ads.pdm.bookspdm.model.Book

class BookAdapter(context: Context,
                  private val bookList: MutableList<Book>):
    ArrayAdapter<Book>(context, R.layout.tile_book, bookList) {

        private data class BookTileHolder(
            val titleTv: TextView,
            val firstAuthorTv: TextView,
            val editionTv: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        lateinit var tbb: TileBookBinding

        // pegar o livro que vai ser usado para preencher a celula
        val book = bookList[position]

        //verificando se a celula ja foi inflada ou nao
        var bookTile = convertView
        if(bookTile == null){
            //Se nao foi, infla uma nova celula
            tbb = TileBookBinding.inflate(context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false)

           bookTile = tbb.root

            // Criar um holder para a nova celula
            val newBookTileHolder = BookTileHolder(
                tbb.titleTv,
                tbb.firstAuthorTv,
                tbb.editionTv
            )
            //Associar holder a nova celula
            bookTile.tag = newBookTileHolder

        }
        // preenche os valores da celula com o novo livro
        val holder = bookTile?.tag as BookTileHolder
        holder?.let{
            it.titleTv.text = book.title
            it.firstAuthorTv.text = book.firstAuthor
            it.editionTv.text = book.edition.toString()
        }

        //Retorna a celula preenchida

        return bookTile
    }
}