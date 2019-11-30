package co.rahulchowdhury.addtothings.ui.state

sealed class AddTaskState
object Added : AddTaskState()
object Adding : AddTaskState()
object UnableToAdd : AddTaskState()
