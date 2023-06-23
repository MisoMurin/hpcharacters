package com.example.hpcharacters.ui.view.error

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hpcharacters.R
import com.example.hpcharacters.ui.theme.Purple40

@Composable
fun ErrorView(
    @DrawableRes
    errorIconId: Int = R.drawable.ic_error,
    @StringRes
    errorMessageId: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = errorIconId),
            contentDescription = "error view"
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(id = errorMessageId),
            fontSize = 24.sp,
            color = Purple40,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun ErrorViewPreview() {
    ErrorView(errorMessageId = R.string.error_character_detail_no_data)
}
