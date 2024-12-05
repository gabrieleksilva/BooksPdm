package br.edu.scl.ifsp.ads.pdm.bookspdm.model

interface BookDao {

    fun createBook(book: Book): Long //retorna o id da linha.
    fun retrieveBook(isbn: String): Book
    fun retrieveBooks(): MutableList<Book>
    fun updateBook(book: Book): Int
    fun deleteBook(isbn: String): Int
}