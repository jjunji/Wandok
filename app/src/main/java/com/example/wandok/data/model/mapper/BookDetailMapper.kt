package com.example.wandok.data.model.mapper

import com.example.wandok.common.extension.removeTag
import com.example.wandok.data.model.BookDetail
import com.example.wandok.data.model.local.BookDetailEntity
import com.example.wandok.data.model.local.TableOfContent
import com.example.wandok.data.model.remote.BookDetailResponse
import java.util.Date

object BookDetailMapper {
    // response to model
    fun mapToBookDetail(bookDetailResponse: BookDetailResponse, bigImage: String? = ""): BookDetail {
        val bookDetail = bookDetailResponse.item
        val tableOfContents = bookDetail
            .bookInfo
            .tableOfContentsJson
            .removeTag()
            .trim()
            .split("\n")
            .filter {
                it.isNotEmpty() && !it.startsWith("===")
            }
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
            image = if(bigImage.isNullOrEmpty()) bookDetail.image else bigImage,
            publisher = bookDetail.publisher,
            tableOfContents = tableOfContents
        )
    }

    // entity to model
    fun mapToBookDetail(entity: BookDetailEntity): BookDetail {
        return BookDetail(
            isbn = entity.isbn,
            title = entity.title,
            author = entity.author,
            image = entity.image,
            publisher = entity.publisher,
            tableOfContents = entity.tableOfContents ?: emptyList(),
            registrationTimeMillis = entity.registrationDate.time,
            progress = entity.progress,
            targetStartTimeMillis = entity.targetStartTimeMillis,
            targetEndTimeMillis = entity.targetEndTimeMillis,
            wandokTimeMillis = entity.wandokTimeMillis
        )
    }

    // model to entity
    fun BookDetail.mapToEntity(): BookDetailEntity {
        return BookDetailEntity(
            isbn = this.isbn,
            title = this.title,
            author = this.author,
            image = this.image,
            publisher = this.publisher,
            tableOfContents = this.tableOfContents,
            registrationDate = Date(this.registrationTimeMillis),
            progress = this.progress,
            targetStartTimeMillis = this.targetStartTimeMillis,
            targetEndTimeMillis = this.targetEndTimeMillis,
            wandokTimeMillis = this.wandokTimeMillis
        )
    }
}