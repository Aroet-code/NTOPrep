package ru.myitschool.work.ui.screen.book

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.myitschool.work.R
import ru.myitschool.work.core.TestIds
import ru.myitschool.work.ui.theme.WorkTheme
import kotlin.collections.listOf

@Composable
fun PlaceTab(modifier: Modifier = Modifier, date: String, onClick: () -> Unit, selected: Boolean) {
    Tab(
        selected = selected,
        modifier = modifier,
        onClick = {
            onClick()
        }
    ) {
        Column(
//            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        )
        {
//            if (selected)
//            {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 12.dp),
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
//                )
//                {
                    Text(
                        modifier = Modifier.testTag(TestIds.Book.ITEM_DATE),
                        text = date,
                    )
//                }
//                Spacer(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(40.dp)
//                )
//                TextField(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    value = dateInputText,
//                    onValueChange = {
//                        dateInputText = it
//                        //                viewModel.onIntent(AuthIntent.TextInput(it))
//                    },
////                label = { Text(stringResource(R.string.place_label)) }
//                    label = { Text("Date") }
//                )
//            }
//            else
//            {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//                )
//                {
//                    Text("Place")
//                }
//            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreen(
    viewModel: BookScreenViewModel = viewModel(),
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.booking_title))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        },
                        modifier = Modifier.testTag(TestIds.Book.BACK_BUTTON)
                    )
                    {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back button"
                    )
                    }
                }
            )
        }
    )
    { innerPadding ->
        var topPadding: Dp = 0.dp
        val tabs = listOf(
            "03.12",
            "04.12",
            "06.10",
            "05.12",
            "07.12",
            "08.10"
        )
        var isEmpty by remember { mutableStateOf(false) }
        var selectedIndex by remember { mutableStateOf(0) }
        var selected by remember { mutableStateOf(0) }
        var error by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            PrimaryScrollableTabRow(
                selectedTabIndex = selected,
                tabs = {
                    tabs.forEachIndexed { index, name ->
                        PlaceTab(
                            modifier = Modifier
                                .padding(24.dp)
//                                .width(120.dp)
                                .fillMaxHeight()
                                .testTag("book_date_pos_$index"),
                            date = name,
                            selected = index == selected,
                            onClick = {
                                selected = index
                            }
                        )
                    }
                }
            )
            if (!error) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                )
                {
                    if (!isEmpty) {
                        LazyColumn(
                            modifier = Modifier
                                .padding(horizontal = 50.dp)
                                .padding(bottom = 100.dp)
                        )
                        {
                            itemsIndexed(
                                listOf(
                                    "Position 1",
                                    "Position 2",
                                    "Position 3"
                                )
                            ) { index, item ->
                                if (index == 0)
                                    topPadding = 12.dp
                                else
                                    topPadding = 0.dp
                                BookCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp)
                                        .padding(top = topPadding)
                                        .testTag("book_place_pos_$index"),
                                    selected = index == selectedIndex,
                                    placeName = item,
                                    onClick =
                                        {
                                            selectedIndex = index
                                        },
                                    onCheckedChange =
                                        {
                                            selectedIndex = index
                                        }
                                )
                            }
                        }
                        Button(
                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxWidth()
                                .testTag(TestIds.Book.BOOK_BUTTON),
                            onClick = {
                                navController.navigateUp()
                            },
                            enabled = true
                        ) {
                            Text(stringResource(R.string.book_button))
                        }
                    } else {
                        EmptyLabel(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
            else
            {
                ErrorLabel(

                )
                BookRefreshButton(
                    onClick =
                    {

                    }
                )
            }
        }
    }
}

@Composable
fun EmptyLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .testTag(TestIds.Book.EMPTY),
        text = stringResource(R.string.empty_label),
        textAlign = TextAlign.Center
    )
}

@Composable
fun ErrorLabel(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .testTag(TestIds.Book.ERROR),
        text = stringResource(R.string.book_error)
    )
}

@Composable
fun BookRefreshButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .testTag(TestIds.Book.REFRESH_BUTTON)
//            .padding(24.dp)
            .width(120.dp),
        onClick = {
            onClick()
        },
        enabled = true
    ) {
        Text(stringResource(R.string.refresh_button))
    }
}

@Composable
fun BookCard(modifier: Modifier = Modifier, selected: Boolean,
             placeName: String, onClick: () -> Unit, onCheckedChange: () -> Unit) {
    Row(
        modifier = modifier
            .clickable(
                onClick =
                    {
                    onClick()
                }
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            modifier = Modifier.testTag(TestIds.Book.ITEM_PLACE_TEXT),
            text = placeName
        )
        Checkbox(
            modifier = Modifier
                .selectable(
                    selected = selected,
                    onClick = {
                        onCheckedChange()
                    }
                )
                .testTag(TestIds.Book.ITEM_PLACE_SELECTOR),
            checked = selected,
            onCheckedChange =
                {
                onCheckedChange()
            }
        )
    }
}

@Preview
@Composable
private fun BookScreenPreview() {
    WorkTheme {
        BookScreen(
            navController = rememberNavController()
        )
    }
}