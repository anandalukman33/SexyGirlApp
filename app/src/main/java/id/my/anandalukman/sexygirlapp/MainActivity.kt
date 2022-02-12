package id.my.anandalukman.sexygirlapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import id.my.anandalukman.sexygirlapp.view.SexyViewModel
import dagger.hilt.android.AndroidEntryPoint
import id.my.anandalukman.sexygirlapp.ui.SexyAppTheme

@AndroidEntryPoint
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SexyAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    val viewModel: SexyViewModel = hiltViewModel()
                    val girl = viewModel.state.value.sexyBean
                    val isLoading = viewModel.state.value.isLoading
                    girl?.let {
                        Image(
                            painter = rememberImagePainter(
                                data = girl.imageUrl,
                                builder = { crossfade(true) }
                            ),
                            contentDescription = "Girl"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = girl.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = girl.description)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Button(
                        onClick = viewModel::getRandomGirl,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Next Girl!")
                    }
                    Spacer(Modifier.height(8.dp))
                    if(isLoading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}