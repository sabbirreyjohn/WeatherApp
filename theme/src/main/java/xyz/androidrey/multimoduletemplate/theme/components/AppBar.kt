package xyz.androidrey.multimoduletemplate.theme.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import xyz.androidrey.multimoduletemplate.theme.primaryContainerLight
import xyz.androidrey.multimoduletemplate.theme.primaryLight

@Composable
fun AppBar(
    title: String,
    navIcon: ImageVector? = null,
    onNav: () -> Unit = {},
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryContainerLight,
            titleContentColor = primaryLight,
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            navIcon?.let {
                IconButton(onClick = { onNav() }) {
                    Icon(navIcon, contentDescription = "Nav Icon")
                }
            }
        },
    )
}
