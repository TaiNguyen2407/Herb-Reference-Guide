package fi.metropolia.herbreferenceguide.Plant;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.MainActivity;
import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;
import fi.metropolia.herbreferenceguide.database.AppDatabase;
import fi.metropolia.herbreferenceguide.database.Plant;

/**
 * <h1>Display all plants</h1>
 * This activity displays all plants stored in room database.
 * Recycler View and event click listener were used
 *
 * @author Tai Nguyen, Giao Ngo
 * @version  1.0
 * @since 2022-02-21
 */

public class PlantActivity extends AppCompatActivity implements RecyclerViewInterface {
    private ArrayList<Plant> plantList;
    private AppDatabase database;
    public static final String PLANT_ITEM = "plant item";

    /**
     * Menu title, plant data is displayed. Event click listener registered for floating buttons
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_list);
        database = AppDatabase.getINSTANCE(PlantActivity.this);
        loadPlantData();
        initRecyclerView();
    }

    /**
     * Load plant type data from database according to user's selection
     */
    private void loadPlantData() {
        Intent intent = getIntent();
        String plantType = intent.getStringExtra(MainActivity.TYPE);
        setTitle(plantType.toUpperCase());
        plantList = (ArrayList<Plant>) database.plantDao().getPlantByType(plantType);
    }


    /**
     * Initializes recyclerview with layout manager(Linear layout)
     * Create a new plant adapter instance and assigns that adapter to recycler view
     * Code reference:
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Create dynamic lists with RecyclerView </a>
     */
    private void initRecyclerView() {
        RecyclerView myVeggieRecyclerView = findViewById(R.id.veggie);
        myVeggieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlantRecyclerViewAdapter plantRecyclerViewAdapter = new PlantRecyclerViewAdapter(
                plantList,
                this,
                this);
        myVeggieRecyclerView.setAdapter(plantRecyclerViewAdapter);
    }


    /**
     * Registers event click listener for recycler view item click
     * @param position
     * @see <a href="https://www.youtube.com/watch?v=7GPUpvcU1FE&ab_channel=PracticalCoding">
     * RecyclerView Item Click | Best Practice Way</a>
     */
    @Override
    public void onItemClick(int position) {
        Plant plant = plantList.get(position);
        Plant plantItem = database.plantDao().getPlantByName(plant.getPlantName());
        Intent intent = new Intent(PlantActivity.this, ItemDisplayActivity.class);
        intent.putExtra(PLANT_ITEM, plantItem);
        startActivity(intent);
    }
}
