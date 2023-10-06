package com.example.litttlelemoncompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.litttlelemoncompose.DishRepository.dishes
import com.example.litttlelemoncompose.ui.theme.LittleLemonColor

@Composable
fun LowerPanel(navController: NavHostController) {
    WeeklySpecialCard()
    LazyColumn {
        items(dishes) {dish ->
            MenuDish(navController, dish)
        }
    }
}

@Composable
fun WeeklySpecialCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.weekly_special),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .padding(8.dp),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDish(navController: NavHostController? = null, dish: Dish) {
    Card(
        onClick =  { navController?.navigate(route = DishDetails.route + "/${dish.id}") }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(
                    text = dish.name,
                    style = MaterialTheme.typography.displayMedium,
                )
                Text(
                    text = dish.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 5.dp),
                )
                Text(
                    text = "$${dish.price}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Image(
                painter = painterResource(id = dish.imageResource),
                contentDescription = "",
                Modifier.clip(RoundedCornerShape(10.dp)),
            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
        color = LittleLemonColor.yellow,
    )
}
