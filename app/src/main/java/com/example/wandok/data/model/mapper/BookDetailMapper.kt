package com.example.wandok.data.model.mapper

import com.example.wandok.common.extension.removeTag
import com.example.wandok.data.model.dao.BookDetailResult
import com.example.wandok.data.model.dto.BookDetailResultDTO

class BookDetailMapper(
    private val dto: BookDetailResultDTO
) {
    fun map(): BookDetailResult {
        val bookDetail = dto.item.first()
        val tableOfContents = bookDetail
            .bookInfo
            .tableOfContentsJson
            .removeTag()
            .trim()
            .split("\n")
            .filter { it.isNotEmpty() }

        return BookDetailResult(
            title = bookDetail.title,
            author = bookDetail.author,
            description = bookDetail.description,
            image = bookDetail.image,
            publisher = bookDetail.publisher,
            tableOfContents = tableOfContents
        )
    }
}