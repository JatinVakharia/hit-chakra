import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import theme.Purple200
import theme.Purple500

@Composable
fun createDialogContent(
    openDialogCustom: MutableState<Boolean>,
    gameState: State,
    actionFunction: () -> Unit,
    watchAds: () -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(10.dp, 5.dp, 10.dp, 10.dp),
            elevation = 8.dp,
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (gameState == State.Win) "You Win" else "Out of life",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.h4,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = if (gameState == State.Win) "Go to next level and check out your limit."
                        else "Get one by watching ad \n OR \n Retry game",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.body2
                    )
                }
                //.......................................................................
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(Purple200),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    TextButton(onClick = {
                        actionFunction()
                        openDialogCustom.value = false
                    }) {
                        Text(
                            text = if (gameState == State.Win) "Next Level" else "Retry",
                            fontWeight = FontWeight.ExtraBold,
                            color = Purple500,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }

                    if (gameState == State.Loss) {
                        Spacer(modifier = Modifier.width(10.dp))
                        TextButton(onClick = {
                            watchAds()
                            openDialogCustom.value = false
                        }) {

                            Text(
                                "Watch Ad",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}