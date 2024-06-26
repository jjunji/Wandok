package com.example.wandok.data.model

import com.example.wandok.database.TableOfContent

/**
 * @property title 책 제목
 * @property author 저자
 * @property description 설명
 * @property image 책 이미지
 * @property publisher 출판사
 * @property tableOfContents 목차 리스트
 */
data class BookDetail(
    val isbn: String,
    val title: String = "",
    val author: String = "",
    val description: String = "",
    val image: String = "",
    val publisher: String = "",
    val tableOfContents: List<TableOfContent> = emptyList()
)