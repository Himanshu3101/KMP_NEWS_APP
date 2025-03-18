package org.himanshu.kmp_news.ui.search.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_search
import kmp_news_app.composeapp.generated.resources.search
import org.himanshu.kmp_news.theme.mediumPadding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchBarScreen(
    text : String,
    onValueChange: (String) -> Unit,
    onSearch : (String) -> Unit
){
    val focusManager = LocalFocusManager.current

    TextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = mediumPadding),
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.SemiBold
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = null
            )
        },
        placeholder = {
            Text(
                text = stringResource(Res.string.search),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        },
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                focusManager.clearFocus()
                onSearch(text)
            }
        )
    )
}