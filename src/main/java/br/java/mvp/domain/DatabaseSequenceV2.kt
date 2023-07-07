package br.java.mvp.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "database_sequences_v2")
data class DatabaseSequenceV2(
    @Id val id: String?,
    val seq: Long
)