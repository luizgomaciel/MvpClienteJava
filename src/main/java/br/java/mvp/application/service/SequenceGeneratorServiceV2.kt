package br.java.mvp.application.service

interface SequenceGeneratorServiceV2 {
    fun generateSequence(seqName: String?): Long
}