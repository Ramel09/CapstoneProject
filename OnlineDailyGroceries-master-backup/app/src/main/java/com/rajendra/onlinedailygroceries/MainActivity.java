package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rajendra.onlinedailygroceries.adapter.CategoryAdapter;
import com.rajendra.onlinedailygroceries.adapter.DiscountedProductAdapter;
import com.rajendra.onlinedailygroceries.adapter.RecentlyViewedAdapter;
import com.rajendra.onlinedailygroceries.model.Category;
import com.rajendra.onlinedailygroceries.model.DiscountedProducts;
import com.rajendra.onlinedailygroceries.model.RecentlyViewed;

import java.util.ArrayList;
import java.util.List;

import static com.rajendra.onlinedailygroceries.R.drawable.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView discountRecyclerView, categoryRecyclerView, recentlyViewedRecycler;
    DiscountedProductAdapter discountedProductAdapter;
    List<DiscountedProducts> discountedProductsList;

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    RecentlyViewedAdapter recentlyViewedAdapter;
    List<RecentlyViewed> recentlyViewedList;

    TextView allCategory;
    Button button,btnlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // discountRecyclerView = findViewById(R.id.discountedRecycler);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        allCategory = findViewById(R.id.allCategoryImage);
        recentlyViewedRecycler = findViewById(R.id.recently_item);
        button = findViewById(R.id.btn_prod);
        btnlogin=findViewById(R.id.btn_login);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Openlogin();
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,login.class);
                startActivity(i);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllProduct();
            }
        });



        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AllCategory.class);
                startActivity(i);
            }
        });

        // adding data to model
        discountedProductsList = new ArrayList<>();
        discountedProductsList.add(new DiscountedProducts(1, discountberry));
        discountedProductsList.add(new DiscountedProducts(2, discountbrocoli));
        discountedProductsList.add(new DiscountedProducts(3, discountmeat));
        discountedProductsList.add(new DiscountedProducts(4, discountberry));
        discountedProductsList.add(new DiscountedProducts(5, discountbrocoli));
        discountedProductsList.add(new DiscountedProducts(6, discountmeat));

        // adding data to model
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, chocolateicon));
        categoryList.add(new Category(2, flower));
        categoryList.add(new Category(3, clothesicon));
        categoryList.add(new Category(4, icon));
        categoryList.add(new Category(5, cellphone));
        categoryList.add(new Category(6,toys));
        categoryList.add(new Category(7, bundle));


        // adding data to model
       recentlyViewedList = new ArrayList<>();

       recentlyViewedList.add(new RecentlyViewed("Red Ribbon",  "Chocolate cake with Design", "850", redribbon, redribbon));
       recentlyViewedList.add(new RecentlyViewed("boque",  "Boque with sunflowes and ribbon", "980", boque, boque));
       recentlyViewedList.add(new RecentlyViewed("coupleshirt",  "Best for couple", "1230", coupleshirt, coupleshirt));
       recentlyViewedList.add(new RecentlyViewed("teddybear",  "Teddy bear Smooth and beautiful", "450", teddybear, teddybear));

      //  setDiscountedRecycler(discountedProductsList);
        setCategoryRecycler(categoryList);
       setRecentlyViewedRecycler(recentlyViewedList);

    }

    //private void setDiscountedRecycler(List<DiscountedProducts> dataList) {
     //   RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
       // discountRecyclerView.setLayoutManager(layoutManager);
      //  discountedProductAdapter = new DiscountedProductAdapter(this,dataList);
      //  discountRecyclerView.setAdapter(discountedProductAdapter);
  //  }


    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this,recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);

    }
    public  void  openAllProduct(){
        Intent intent= new Intent(this,AllProduct.class);
        startActivity(intent);
    }
    public  void Openlogin(){
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
    }

    //Now again we need to create a adapter and model class for recently viewed items.
    // lets do it fast.
}
