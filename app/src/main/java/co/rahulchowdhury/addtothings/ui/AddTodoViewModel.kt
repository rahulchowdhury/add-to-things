package co.rahulchowdhury.addtothings.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.rahulchowdhury.addtothings.data.model.Todo
import co.rahulchowdhury.addtothings.data.repo.ThingsRepository
import co.rahulchowdhury.addtothings.ui.state.AddTodoState
import co.rahulchowdhury.addtothings.ui.state.Added
import co.rahulchowdhury.addtothings.ui.state.Adding
import co.rahulchowdhury.addtothings.ui.state.UnableToAdd
import kotlinx.coroutines.launch

class AddTodoViewModel(
    private val thingsRepository: ThingsRepository
) : ViewModel() {
    private val _addTodoState: MutableLiveData<AddTodoState> = MutableLiveData()
    val addTodoState: LiveData<AddTodoState> = _addTodoState

    fun addTodo(
        task: String,
        note: String
    ) {
        _addTodoState.value = Adding

        viewModelScope.launch {
            try {
                val todo = Todo(task, note)
                thingsRepository.addToThings(todo)

                _addTodoState.value = Added
            } catch (exception: Exception) {
                _addTodoState.value = UnableToAdd
            }
        }
    }
}
