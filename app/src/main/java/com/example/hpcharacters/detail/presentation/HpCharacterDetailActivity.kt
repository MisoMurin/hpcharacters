package com.example.hpcharacters.detail.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hpcharacters.characters.domain.HpCharacter
import com.example.hpcharacters.ui.theme.HPCharactersTheme

class HpCharacterDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HPCharactersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }

    companion object {
        const val EXTRA_HP_CHARACTER = "extra_hp_character"
    }
}

@Composable
fun CharacterDetailView(hpCharacter: HpCharacter) {

}

@Preview
@Composable
fun CharacterDetailViewPreview() {
    CharacterDetailView(
        hpCharacter = HpCharacter(
            name = "Harry Potter",
            actor = "Daniel Radcliffe",
            image = "https://ik.imagekit.io/hpapi/harry.jpg"
        )
    )
}
