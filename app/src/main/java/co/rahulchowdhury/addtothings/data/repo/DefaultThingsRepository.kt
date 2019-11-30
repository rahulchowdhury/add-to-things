package co.rahulchowdhury.addtothings.data.repo

import co.rahulchowdhury.addtothings.data.model.Todo
import co.rahulchowdhury.addtothings.data.source.remote.ThingsEmailSource

class DefaultThingsRepository(
    private val thingsEmailSource: ThingsEmailSource
) : ThingsRepository {
    override suspend fun addToThings(
        todo: Todo
    ) = thingsEmailSource.emailToThings(todo)
}
