package co.rahulchowdhury.addtothings.ui.state

sealed class AddTodoState
object Added : AddTodoState()
object Adding : AddTodoState()
object UnableToAdd : AddTodoState()
