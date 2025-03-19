package org.himanshu.kmp_news.ui.setting.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.apply
import kmp_news_app.composeapp.generated.resources.cancel
import kmp_news_app.composeapp.generated.resources.choose_a_theme
import kmp_news_app.composeapp.generated.resources.delete
import kmp_news_app.composeapp.generated.resources.delete_bookmark
import kmp_news_app.composeapp.generated.resources.delete_bookmark_description
import org.himanshu.kmp_news.theme.mediumPadding
import org.himanshu.kmp_news.theme.xLargePadding
import org.himanshu.kmp_news.theme.xSmallPadding
import org.himanshu.kmp_news.utils.Theme
import org.jetbrains.compose.resources.stringResource

@Composable
fun DeleteBookmarkDialog(
    onDeleteBookmark: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(stringResource(Res.string.delete_bookmark))
        },
        text = {
            Text(stringResource(Res.string.delete_bookmark_description))
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = stringResource(Res.string.delete_bookmark),
                tint = MaterialTheme.colorScheme.error
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDeleteBookmark()
                }
            ){
                Text(stringResource(Res.string.delete))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ){
                Text(stringResource(Res.string.cancel))
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDialog(
    currentTheme:String,
    onThemeChange : (Theme) -> Unit,
    onDismissRequest: () -> Unit
){
    var currentSelectedTheme by remember{
        mutableStateOf(Theme.valueOf(currentTheme))
    }

    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        content = {
            Surface (
                modifier = Modifier
                    .wrapContentSize(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ){

                Column(
                    modifier = Modifier.padding(mediumPadding)
                ) {
                    Text(
                        text = stringResource(Res.string.choose_a_theme),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(xSmallPadding)
                    )
                    Theme.entries.forEach { theme ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    currentSelectedTheme = theme
                                },
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = currentSelectedTheme == theme,
                                onClick = {
                                    currentSelectedTheme = theme
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.primary,
//                                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                            Text(
                                text = stringResource(theme.title)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(xLargePadding))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = onDismissRequest
                        ) {
                            Text(stringResource(Res.string.cancel))
                        }

                        Spacer(modifier = Modifier.width(mediumPadding))

                        TextButton(
                            onClick = {
                                onThemeChange(currentSelectedTheme)
                            }
                        ) {
                            Text(stringResource(Res.string.apply))
                        }
                    }
                }
            }
        }
    )
}



