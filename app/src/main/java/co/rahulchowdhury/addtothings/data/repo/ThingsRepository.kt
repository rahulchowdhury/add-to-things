package co.rahulchowdhury.addtothings.data.repo

interface ThingsRepository {
    suspend fun addToThings(
        task: String,
        note: String
    )
}
