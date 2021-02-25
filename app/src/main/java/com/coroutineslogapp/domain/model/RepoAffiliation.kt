package com.coroutineslogapp.domain.model

import java.lang.StringBuilder

private const val COMMA_SEPARATOR = ","

enum class RepoAffiliation(val value: String) {
    OWNER("owner"), COLLABORATOR("collaborator"), ORGANIZATION("organization_member")
}

fun getConcatenatedAffiliation(indices: List<Int> = listOf()): String {
    val concatenatedAffiliation = StringBuilder()
    val affiliationValues = if (indices.isEmpty()) {
        RepoAffiliation.values().toList()
    } else {
        indices.map { indice -> RepoAffiliation.values()[indice] }
    }

    affiliationValues.forEachIndexed { index, _ ->
        if (index != affiliationValues.size - 1) {
            concatenatedAffiliation.append(affiliationValues[index].value).append(COMMA_SEPARATOR)
        } else {
            concatenatedAffiliation.append(affiliationValues[index].value)
        }
    }
    return concatenatedAffiliation.toString()
}

fun getDefaultListOfIndexes(): List<Int> = RepoAffiliation.values().indices.toList()
