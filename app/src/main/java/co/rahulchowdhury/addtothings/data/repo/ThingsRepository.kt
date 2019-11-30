package co.rahulchowdhury.addtothings.data.repo

import co.rahulchowdhury.addtothings.data.model.Todo

interface ThingsRepository {
    suspend fun addToThings(
        todo: Todo
    )
}
