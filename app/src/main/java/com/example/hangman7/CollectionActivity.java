package com.example.hangman7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CollectionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnFood , btnAnimal, btnFamous ,btnCar, btnCountry , btnCity;
    private String word;
    private final String[] Foods = {"pizza ","burger ","sushi ","pasta","pancakes","salad","ice cream","sandwich"};
    private final String[] Animals = {"dog","cat","cow","horse","sheep","chicken","rabbit","duck","goat","pig"};
    private final String[] Famous = {"ronaldo","messi","elon musk","","the rock","omar adam","spongebob","neymar","bibi","trump"};
    private final String[] Cars = {"toyota","ford","honda","chevrolet","BMW","audi","nissan","ferrari","tesla","lamborghini"};
    private final String[] countrys = {"israel","united states","france","italy","japan","brazil","china","canada","india","spain"};
    private final String[] Citys = {"tokyo","paris","mumbai","berlin","tel aviv","holon","bat yam","haifa","jerusalem","ramat gan"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        init();
    }

    private void init() {
        btnFood = findViewById(R.id.btnfood);
        btnAnimal = findViewById(R.id.btnanimal);
        btnFamous = findViewById(R.id.btnfamous);
        btnCar = findViewById(R.id.btncar);
        btnCountry = findViewById(R.id.btncountry);
        btnCity = findViewById(R.id.btncity);

        btnFood.setOnClickListener((this));
        btnAnimal.setOnClickListener((this));
        btnFamous.setOnClickListener((this));
        btnCar.setOnClickListener((this));
        btnCountry.setOnClickListener((this));
        btnCity.setOnClickListener((this));
    }

    @Override
    public void onClick(View v) {
        if (v == btnFood) word = getRandomElement(Foods);
        if (v == btnAnimal) word = getRandomElement(Animals);
        if (v == btnFamous) word = getRandomElement(Famous);
        if (v == btnCar) word = getRandomElement(Cars);
        if (v == btnCountry) word = getRandomElement(countrys);
        if (v == btnCity) word = getRandomElement(Citys);

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("EXTRA_WORD", word);
        intent.putExtra("SAVE_MODE", "collection");
        startActivity(intent);
    }
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_down,R.anim.slide_out_down);
    }


    public static String getRandomElement(String[] list)
    {
        Random random = new Random();
        int randomIndex = random.nextInt(list.length);
        return list[randomIndex];
    }
}

