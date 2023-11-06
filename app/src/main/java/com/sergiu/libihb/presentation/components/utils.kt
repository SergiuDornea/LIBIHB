package com.sergiu.libihb.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sergiu.libihb.R

@Preview
@Composable
fun Logo(modifier: Modifier = Modifier){

    Image(
        painter = painterResource(id = R.drawable.ic_logo_transparent2),
        contentDescription = stringResource(id = R.string.app_logo),
        modifier = Modifier
            .padding(top = 20.dp)
            .size(180.dp),
        alignment = Alignment.TopCenter
    )


}