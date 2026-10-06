package ru.myitschool.work.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.myitschool.work.R
import ru.myitschool.work.core.TestIds
import ru.myitschool.work.ui.filterByDate
import ru.myitschool.work.ui.nav.BookScreenDestination
import ru.myitschool.work.ui.theme.WorkTheme
import java.time.LocalDateTime
import java.time.LocalTime
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import io.ktor.util.collections.getValue

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = viewModel(),
    navController: NavController
) {
    var error by remember { mutableStateOf(false) }
    val name: String = viewModel.name.collectAsState().value
    val image = viewModel.image
    val reservations = viewModel.reservations.toList()
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize()
        )
        {
            if (error)
            {
                ErrorLabel(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (!error) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    )
                    {
                        Image(
                            image,
                            contentDescription = "Avatar image",
                            modifier = Modifier
                                .clip(
                                    shape = CircleShape
                                )
                                .testTag(TestIds.Main.PROFILE_IMAGE)
                        )
                        Spacer(
                            modifier = Modifier.width(20.dp)
                        )
                        Text(
                            text = name,
                            modifier = Modifier.testTag(TestIds.Main.PROFILE_NAME)
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .testTag(TestIds.Main.LOGOUT_BUTTON),
                        onClick = {}
                    )
                    {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Log out button"
                        )
                    }
                }
                Spacer(
                    modifier = Modifier.height(60.dp)
                )
            }
            Column(
                modifier = Modifier.fillMaxSize()
            )
            {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    if (!error) {
                        IconButton(
                            modifier = Modifier.testTag(TestIds.Main.ADD_BUTTON),
                            onClick = {
                                navController.navigate(BookScreenDestination)
                            }
                        )
                        {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = "Add button"
                            )
                        }
                        Text(
                            text = stringResource(R.string.booked_places)
                        )
                    }
                    IconButton(
                        modifier = Modifier.testTag(TestIds.Main.REFRESH_BUTTON),
                        onClick = {
                            viewModel.updateData()
                        }
                    )
                    {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh button"
                        )
                    }
                }
                if (!error) {
                    LazyColumn(
                        Modifier.fillMaxHeight()
                    )
                    {
                        itemsIndexed(
                            filterByDate(reservations))
                        { index, it ->
                            ReservationCard(
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .fillMaxWidth()
                                    .testTag("main_book_pos_$index"),
                                ri = it
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.testTag(TestIds.Main.ERROR),
        text = stringResource(R.string.data_loading_error_main)
    )
}

@Composable
private fun ReservationCard(modifier: Modifier = Modifier, ri: ReservationInfo)
{
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            modifier = Modifier
                .weight(1f)
                .testTag(TestIds.Main.ITEM_PLACE),
            text = ri.place,
        )
        Text(
            modifier = Modifier.testTag(TestIds.Main.ITEM_DATE),
            text = "${ri.dateTime.dayOfMonth}.${ri.dateTime.monthValue}.${ri.dateTime.year}"
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    WorkTheme () {
        MainScreen(
            navController = rememberNavController()
        )
    }
}