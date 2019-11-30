package co.rahulchowdhury.addtothings.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.rahulchowdhury.addtothings.data.model.Todo
import co.rahulchowdhury.addtothings.data.repo.ThingsRepository
import co.rahulchowdhury.addtothings.ui.state.AddTaskState
import co.rahulchowdhury.addtothings.ui.state.Added
import co.rahulchowdhury.addtothings.ui.state.Adding
import co.rahulchowdhury.addtothings.ui.state.UnableToAdd
import kotlinx.coroutines.launch

class AddTodoViewModel(
    private val thingsRepository: ThingsRepository
) : ViewModel() {
    private val _addTaskState: MutableLiveData<AddTaskState> = MutableLiveData()
    val addTaskState: LiveData<AddTaskState> = _addTaskState

    fun addTodo(
        task: String,
        note: String
    ) {
        _addTaskState.value = Adding

        viewModelScope.launch {
            try {
                val todo = Todo(task, note)
                thingsRepository.addToThings(todo)

                _addTaskState.value = Added
            } catch (exception: Exception) {
                _addTaskState.value = UnableToAdd
            }
        }
    }
}
