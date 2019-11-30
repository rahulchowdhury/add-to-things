package co.rahulchowdhury.addtothings.ui.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.rahulchowdhury.addtothings.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

abstract class ViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
}
