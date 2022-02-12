package id.my.anandalukman.sexygirlapp.view

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.my.anandalukman.sexygirlapp.bean.SexyBean
import id.my.anandalukman.sexygirlapp.network.SexyGirlApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SexyViewModel @Inject constructor(private val api: SexyGirlApi) : ViewModel() {

    private val _state = mutableStateOf(SexyGirlState())
    val state: State<SexyGirlState> = _state

    init {
        getRandomGirl()
    }

    fun getRandomGirl() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    sexyBean = api.getRandomGirl(),
                    isLoading = false
                )
            } catch (e: Exception) {
                Log.e("SexyViewModel", "getRandomGirl: ", e)
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }


    data class SexyGirlState(
        val sexyBean: SexyBean? = null,
        val isLoading: Boolean = false
    )
}