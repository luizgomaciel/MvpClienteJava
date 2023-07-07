package br.java.mvp.application.service.impl

import br.java.mvp.application.service.SequenceGeneratorServiceV2
import br.java.mvp.domain.DatabaseSequenceV2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import java.util.*

@Service
class SequenceGeneratorServiceV2Impl @Autowired constructor(private val mongoOperations: MongoOperations) :
    SequenceGeneratorServiceV2 {
    override fun generateSequence(seqName: String?): Long {
        val counter = mongoOperations.findAndModify(
            Query.query(Criteria.where("_id").`is`(seqName)),
            Update().inc("seq", 1),
            FindAndModifyOptions.options().returnNew(true).upsert(true),
            DatabaseSequenceV2::class.java
        )
        return if (!Objects.isNull(counter)) counter.seq else 1
    }
}