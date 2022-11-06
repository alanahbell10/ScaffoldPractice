package lana.bell.scaffoldpractice

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import lana.bell.scaffoldpractice.ui.theme.ScaffoldPracticeTheme
import lana.bell.scaffoldpractice.R
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
            val scope = rememberCoroutineScope()
            ModalBottomSheetLayout(
                sheetContent = {
                    BottomSheetContent()
                },
                sheetState = modalBottomSheetState
                // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
            ) {
                Scaffold(
                    topBar = { TopBar() },
                    backgroundColor = colorResource(id = R.color.purple_700)
                ) { padding ->  // We need to pass scaffold's inner padding to content. That's why we use Box.
                    Box(modifier = Modifier.padding(padding)) {
                        MainScreen(scope = scope, state = modalBottomSheetState)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(scope: CoroutineScope, state: ModalBottomSheetState) {
    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.purple_700))
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.purple_200),
                contentColor = Color.White
            ),
            onClick = {
                scope.launch {
                    state.show()
                }
            }) {
            Text(text = "Open Modal Bottom Sheet Layout")
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(title = { Text(text= "Top App Bar") })
}

@Composable
fun BottomSheetContent() {
    val context = LocalContext.current
    Column {
        Row( modifier = Modifier.padding(12.dp)) {
            Icon(
                painter = painterResource(R.drawable.trashcan),
                contentDescription = "trashcan"
            )
            Text("Are you sure you want to delete all selected items?")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldPracticeTheme {
        TopBar()
    }
}