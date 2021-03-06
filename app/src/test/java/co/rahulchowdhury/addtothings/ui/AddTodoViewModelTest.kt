package co.rahulchowdhury.addtothings.ui

import co.rahulchowdhury.addtothings.LiveDataTestUtil.getValue
import co.rahulchowdhury.addtothings.data.model.Todo
import co.rahulchowdhury.addtothings.data.repo.ThingsRepository
import co.rahulchowdhury.addtothings.ui.base.ViewModelTest
import co.rahulchowdhury.addtothings.ui.state.Added
import co.rahulchowdhury.addtothings.ui.state.Adding
import co.rahulchowdhury.addtothings.ui.state.UnableToAdd
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddTodoViewModelTest : ViewModelTest() {
    @Mock
    lateinit var thingsRepository: ThingsRepository

    private lateinit var addTodoViewModel: AddTodoViewModel
    private lateinit var sampleTodo: Todo

    @Before
    fun setUp() {
        addTodoViewModel = AddTodoViewModel(thingsRepository)

        sampleTodo = Todo(
            task = "Test the app",
            note = "See if things are working"
        )
    }

    @Test
    fun `should set Added state when task is added`() = runBlocking {
        `when`(thingsRepository.addToThings(sampleTodo)).thenReturn(Unit)

        addTodoViewModel.addTodo(
            task = sampleTodo.task,
            note = sampleTodo.note
        )

        val addTodoState = getValue(addTodoViewModel.addTodoState)
        assertThat(addTodoState).isEqualTo(Added)

        verify(thingsRepository).addToThings(sampleTodo)

        verifyNoMoreInteractions(thingsRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should set Adding state when task is being added`() = runBlocking {
        `when`(thingsRepository.addToThings(sampleTodo)).thenReturn(Unit)

        mainCoroutineRule.pauseDispatcher()

        addTodoViewModel.addTodo(
            task = sampleTodo.task,
            note = sampleTodo.note
        )

        assertThat(getValue(addTodoViewModel.addTodoState)).isEqualTo(Adding)

        mainCoroutineRule.resumeDispatcher()

        assertThat(getValue(addTodoViewModel.addTodoState)).isEqualTo(Added)
    }

    @Test
    fun `should set UnableToAdd state when task can't be added`() = runBlocking {
        `when`(thingsRepository.addToThings(sampleTodo)).thenThrow(RuntimeException())

        addTodoViewModel.addTodo(
            task = sampleTodo.task,
            note = sampleTodo.note
        )

        val addTodoState = getValue(addTodoViewModel.addTodoState)
        assertThat(addTodoState).isEqualTo(UnableToAdd)

        verify(thingsRepository).addToThings(sampleTodo)

        verifyNoMoreInteractions(thingsRepository)
    }

    @Test
    fun `should set UnableToAdd state when task is empty`() = runBlocking {
        addTodoViewModel.addTodo(
            task = "",
            note = sampleTodo.note
        )

        val addTodoState = getValue(addTodoViewModel.addTodoState)
        assertThat(addTodoState).isEqualTo(UnableToAdd)

        verifyNoMoreInteractions(thingsRepository)
    }

    @Test
    fun `should set "No Note" when note is empty`() = runBlocking {
        val noNoteTodo = sampleTodo.copy(note = "No note")
        `when`(thingsRepository.addToThings(noNoteTodo)).thenReturn(Unit)

        addTodoViewModel.addTodo(
            task = sampleTodo.task,
            note = ""
        )

        val addTodoState = getValue(addTodoViewModel.addTodoState)
        assertThat(addTodoState).isEqualTo(Added)

        verify(thingsRepository).addToThings(noNoteTodo)

        verifyNoMoreInteractions(thingsRepository)
    }
}
