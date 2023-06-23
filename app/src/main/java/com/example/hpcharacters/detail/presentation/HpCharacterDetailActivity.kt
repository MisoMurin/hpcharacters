package com.example.hpcharacters.detail.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hpcharacters.R
import com.example.hpcharacters.characters.domain.HpCharacter
import com.example.hpcharacters.ui.theme.Background
import com.example.hpcharacters.ui.theme.HPCharactersTheme
import com.example.hpcharacters.ui.theme.Purple40
import com.example.hpcharacters.ui.view.error.ErrorView


class HpCharacterDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HPCharactersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Background
                ) {
                    intent.extras?.get(EXTRA_HP_CHARACTER).let { hpCharacter ->
                        if (hpCharacter != null) {
                            CharacterDetailView(hpCharacter = hpCharacter as HpCharacter)
                        } else {
                            ErrorView(errorMessageId = R.string.error_character_detail_no_data)
                        }
                    }
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop,
            model = hpCharacter.image,
            contentDescription = hpCharacter.name,
            placeholder = painterResource(id = R.drawable.character_placeholder),
            error = painterResource(id = R.drawable.character_placeholder),
            fallback = painterResource(id = R.drawable.character_placeholder),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier.padding(8.dp),
            text = hpCharacter.name,
            fontSize = 28.sp,
            color = Purple40,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier.padding(8.dp),
            text = "${stringResource(id = R.string.character_detail_played_by)} ${hpCharacter.actor}",
            fontSize = 24.sp,
            color = Purple40,
        )
    }
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

