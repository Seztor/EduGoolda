package ru.itmo.edugoolda.data.solutions.api

import me.aartikov.replica.paged.PagedReplica

interface SolutionRepository {
    val solutionListReplica : PagedReplica<SolutionList>
}