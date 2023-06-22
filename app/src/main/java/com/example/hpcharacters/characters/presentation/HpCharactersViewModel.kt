package com.example.hpcharacters.characters.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hpcharacters.characters.data.mappers.toHpCharacter
import com.example.hpcharacters.characters.data.remote.HpCharactersRepository
import com.example.hpcharacters.characters.domain.HpCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HpCharactersViewModel
@Inject
constructor (
    private val hpCharactersRepository: HpCharactersRepository
): ViewModel() {

    private val _hpCharacters: MutableList<HpCharacter> = mutableStateListOf()
    val hpCharacters: List<HpCharacter> = _hpCharacters

    var state: MutableState<State> = mutableStateOf(State.LOADING)

    var errorMessage: MutableState<String> = mutableStateOf("")

    fun loadHpCharacters() {
        viewModelScope.launch {
            try {
                hpCharactersRepository.getHpCharacters().let { hpCharacterDtos ->
                    _hpCharacters.clear()

                    val charactersSortedByName = hpCharacterDtos
                        .map { hpCharacterDto -> hpCharacterDto.toHpCharacter() }
                        .sortedBy { hpCharacter -> hpCharacter.name }

                    _hpCharacters.addAll(charactersSortedByName)

                    state.value = State.SUCCESS
                }
            } catch (e: Exception) {
                state.value = State.ERROR
                errorMessage.value = e.message.toString()
            }
        }
    }

    enum class State {
        LOADING,
        SUCCESS,
        ERROR
    }
}
