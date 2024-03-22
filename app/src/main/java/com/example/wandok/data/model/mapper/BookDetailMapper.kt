package com.example.wandok.data.model.mapper

import com.example.wandok.common.extension.removeTag
import com.example.wandok.data.model.dao.BookDetail
import com.example.wandok.data.model.dto.BookDetailDTO

class BookDetailMapper(
    private val dto: BookDetailDTO
) {
    fun map(): BookDetail {
        val bookDetail = dto.item.first()
        val tableOfContents = bookDetail
            .bookInfo
            .tableOfContentsJson
            .removeTag()
            .trim()
            .split("\n")
            .filter { it.isNotEmpty() }

        return BookDetail(
            title = bookDetail.title,
            author = bookDetail.author,
            description = bookDetail.description,
            image = bookDetail.image,
            publisher = bookDetail.publisher,
            tableOfContents = tableOfContents
        )
    }
}