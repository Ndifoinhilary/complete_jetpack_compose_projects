package com.hilaryd.tictactoagame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hilaryd.tictactoagame.ui.theme.TicTacToaGameTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToaGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TTTScreen()
                }
            }
        }
    }
}

@Composable
fun TTTScreen() {
// true players turns and false for AI's turn
    val playerTurn = remember {
        mutableStateOf(true)
    }

    val moves = remember {
        mutableStateListOf<Boolean?>(true, null, false, null, true, null, null, false, null)
    }

    val onTap: (Offset) -> Unit = {
        if (playerTurn.value){
            val x = (it.x / 333).toInt()
            val y = (it.x / 333).toInt()
            val position = y * 3 + x
            if (moves[position] == null){
                moves[position] = true
                playerTurn.value = false
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Tic Tac Toe Game", modifier = Modifier.padding(16.dp), fontSize = 24.sp)
        //display who's turn to move
        Header(playTurn = playerTurn.value)

//    true for player moves,  false for AI move and null for no move
        Board(moves = moves, onTap = onTap)

        if(!playerTurn.value){
            CircularProgressIndicator(color = Color.Red, modifier = Modifier.padding(16.dp))

            val coroutineScope = rememberCoroutineScope()

            LaunchedEffect(Unit) {
                coroutineScope.launch {
                   kotlinx.coroutines.delay(1500L)

                    while(true){
                        val i = Random.nextInt(9)
                        if (moves[i] == null){
                            moves[i] = false
                            playerTurn.value = true
                            break
                        }
                    }

                }
            }
        }
    }

}


@Composable
fun Header(playTurn: Boolean) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        val playerBoxColor = if (playTurn) Color.Blue else Color.LightGray
        val aiBoxColor = if (playTurn) Color.LightGray else Color.Red
        Box(
            modifier = Modifier
                .width(80.dp)
                .background(playerBoxColor)
        ) {
            Text(
                text = "Player", modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(80.dp))
        Box(
            modifier = Modifier
                .width(80.dp)
                .background(aiBoxColor)
        ) {
            Text(
                text = "Ai ", modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Center)
            )
        }
    }
}


@Composable
fun Board(moves: List<Boolean?>, onTap: (Offset) -> Unit) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(32.dp)
            .background(Color.LightGray)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = onTap
                )
            }
    ) {

        Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize(1f)) {
            Row(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth(1f)
                    .background(Color.Black)
            ) {

            }
            Row(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth(1f)
                    .background(Color.Black)
            ) {

            }

        }
        Row(modifier = Modifier.fillMaxSize(1f), horizontalArrangement = Arrangement.SpaceEvenly) {
            Column(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxSize(1f)
                    .background(Color.Black)
            ) {}
            Column(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxSize(1f)
                    .background(Color.Black)
            ) {}
        }

        Column(modifier = Modifier.fillMaxSize(1f)) {
            for (  i in 0..2){
                Row(modifier = Modifier.weight(1f)) {
                    for (j in 0..2){
                        Column(modifier = Modifier.weight(1f)) {
                            showMoves(move = moves[i * 3 + j])
                        }
                    }
                }
            }
        }
    }


}


@Composable
fun showMoves(move: Boolean?) {
    when (move) {
        true -> Image(
            painter = painterResource(id = R.drawable.ic_x),
            contentDescription = "Player move",
            modifier = Modifier.fillMaxSize(1f),
            colorFilter = ColorFilter.tint(Color.Blue)
        )

        false -> Image(
            painter = painterResource(id = R.drawable.ic_o),
            contentDescription = "Ai move",
            modifier = Modifier.fillMaxSize(1f),
            colorFilter = ColorFilter.tint(Color.Red)
        )
        null ->  Image(
            painter = painterResource(id = R.drawable.ic_null),
            contentDescription = "null move",
            modifier = Modifier.fillMaxSize(1f),
            colorFilter = ColorFilter.tint(Color.Blue)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToaGameTheme {
        TTTScreen()
    }
}