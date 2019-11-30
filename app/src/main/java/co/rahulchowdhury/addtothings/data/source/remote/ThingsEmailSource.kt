package co.rahulchowdhury.addtothings.data.source.remote

interface ThingsEmailSource {
    suspend fun emailToThings(
        task: String,
        note: String
    )
}
