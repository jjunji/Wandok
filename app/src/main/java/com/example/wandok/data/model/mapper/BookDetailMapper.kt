package com.example.wandok.data.model.mapper

import com.example.wandok.common.extension.removeTag
import com.example.wandok.data.model.BookDetail
import com.example.wandok.data.model.response.BookDetailResponse
import com.example.wandok.database.TableOfContent

object BookDetailMapper {
    fun mapToBookDetail(bookDetailResponse: BookDetailResponse) : BookDetail {
        val bookDetail = bookDetailResponse.item.first()
        val tableOfContents = bookDetail
            .bookInfo
            .tableOfContentsJson
            .removeTag()
            .trim()
            .split("\n")
            .filter { it.isNotEmpty() }
            .mapIndexed { index, tableOfContent ->
                TableOfContent(
                    index = index,
                    subTitle = tableOfContent,
                    read = false
                )
            }

        return BookDetail(
            isbn = bookDetail.isbn,
            title = bookDetail.title,
            author = bookDetail.author,
            description = bookDetail.description,
            image = bookDetail.image,
            publisher = bookDetail.publisher,
            tableOfContents = tableOfContents
        )
    }
}