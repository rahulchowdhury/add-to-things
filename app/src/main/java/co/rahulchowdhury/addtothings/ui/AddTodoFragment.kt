package co.rahulchowdhury.addtothings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import co.rahulchowdhury.addtothings.R
import co.rahulchowdhury.addtothings.ui.state.AddTodoState
import co.rahulchowdhury.addtothings.ui.state.Added
import co.rahulchowdhury.addtothings.ui.state.Adding
import co.rahulchowdhury.addtothings.ui.state.UnableToAdd
import co.rahulchowdhury.addtothings.util.hideKeyboard
import co.rahulchowdhury.addtothings.util.showSnackbar
import kotlinx.android.synthetic.main.fragment_add_todo.*
import org.koin.android.viewmodel.ext.android.viewModel

class AddTodoFragment : Fragment() {
    val addTodoViewModel: AddTodoViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        LayoutInflater.from(context).inflate(
            R.layout.fragment_add_todo,
            container,
            false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addTodoViewModel.addTodoState.observe(viewLifecycleOwner, Observer { addTodoState ->
            render(addTodoState)
        })

        addTodoButton.setOnClickListener {
            addTodoViewModel.addTodo(
                task = todoTask.text.toString(),
                note = todoNote.text.toString()
            )
        }
    }

    private fun render(addTodoState: AddTodoState) {
        when (addTodoState) {
            is Adding -> {
                hideKeyboard()
            }
            is Added -> {
                todoTask.text.clear()
                todoNote.text.clear()

                todoTask.requestFocus()

                showSnackbar(R.string.message_todo_added)
            }
            is UnableToAdd -> {
                showSnackbar(R.string.message_todo_not_added)
            }
        }
    }
}
