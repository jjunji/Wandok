package com.example.wandok.data.model.mapper

import com.example.wandok.common.extension.removeTag
import com.example.wandok.data.model.BookDetail
import com.example.wandok.data.model.local.BookDetailEntity
import com.example.wandok.data.model.local.TableOfContent
import com.example.wandok.data.model.remote.BookDetailResponse

object BookDetailMapper {
    // response to model
    fun mapToBookDetail(bookDetailResponse: BookDetailResponse): BookDetail {
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
            tableOfContents = tableOfContents,
            registrationTimeMillis = System.currentTimeMillis(),
            progress = 0
        )
    }

    // entity to model
    fun mapToBookDetail(entity: BookDetailEntity) : BookDetail {
        return BookDetail(
            isbn = entity.isbn,
            title = entity.title,
            author = entity.author,
            image = entity.image,
            publisher = entity.publisher,
            tableOfContents = entity.tableOfContents ?: emptyList(),
            registrationTimeMillis = entity.registrationDate.time,
            progress = entity.progress
        )
    }
}