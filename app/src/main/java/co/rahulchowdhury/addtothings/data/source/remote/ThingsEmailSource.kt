package co.rahulchowdhury.addtothings.data.source.remote

import co.rahulchowdhury.addtothings.data.model.Todo

interface ThingsEmailSource {
    suspend fun emailToThings(
        todo: Todo
    )
}
