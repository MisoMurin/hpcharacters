package com.example.hpcharacters.characters.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.hpcharacters.R
import com.example.hpcharacters.characters.domain.HpCharacter
import com.example.hpcharacters.detail.presentation.HpCharacterDetailActivity
import com.example.hpcharacters.detail.presentation.HpCharacterDetailActivity.Companion.EXTRA_HP_CHARACTER
import com.example.hpcharacters.ui.theme.Background
import com.example.hpcharacters.ui.theme.HPCharactersTheme
import com.example.hpcharacters.ui.theme.Purple40
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HpCharactersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HPCharactersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = hiltViewModel<HpCharactersViewModel>()
                    HpCharactersList(viewModel)
                }
            }
        }
    }
}

@Composable
fun HpCharactersList(
    viewModel: HpCharactersViewModel,
) {

    LaunchedEffect(Unit, block = {
        viewModel.loadHpCharacters()
    })

    Box(modifier = Modifier.fillMaxSize()) {

        if (viewModel.state.value == HpCharactersViewModel.State.LOADING) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else if (viewModel.state.value == HpCharactersViewModel.State.ERROR) {

        } else {
            val context = LocalContext.current
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {

                items(viewModel.hpCharacters) { hpCharacter ->
                    HpCharacterItem(hpCharacter = hpCharacter) {
                        context.startActivity(
                            Intent(context, HpCharacterDetailActivity::class.java).apply {
                                putExtra(EXTRA_HP_CHARACTER, hpCharacter)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HpCharacterItem(
    hpCharacter: HpCharacter,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(4.dp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .clip(shape)
            .clickable { onClick() },
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = Background,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .align(alignment = Alignment.CenterVertically),
                contentScale = ContentScale.Crop,
                model = hpCharacter.image,
                contentDescription = hpCharacter.name,
                placeholder = painterResource(id = R.drawable.character_placeholder),
                error = painterResource(id = R.drawable.character_placeholder),
                fallback = painterResource(id = R.drawable.character_placeholder),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = hpCharacter.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                fontSize = 18.sp,
                color = Purple40,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview
@Composable
fun HpCharacterItemPreview() {
    HPCharactersTheme {
        HpCharacterItem(
            hpCharacter = HpCharacter(
                name = "Harry Potter",
                actor = "Daniel Radcliffe",
                image = "https://ik.imagekit.io/hpapi/harry.jpg"
            )
        ) {}
    }
}
