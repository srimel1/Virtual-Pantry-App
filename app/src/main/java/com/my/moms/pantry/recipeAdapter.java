/*
 * Copyright (c) 2020.. Stephanie Rimel
 */

package com.my.moms.pantry;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.apache.commons.text.WordUtils;


/***
 *Custom FirebaseRecyclerAdaptaer
 *
 * Firebase Recycler Adapter is a class from firebase UI
 * to provide the methods to bind, change and display the
 * contents of a firebase database in a recycler view
 */
class recipeAdapter extends FirebaseRecyclerAdapter<recipe, recipeAdapter.recipeViewHolder> {


    Context mContext;

    /***
     * method that tells the class which layout
     * @param parent
     * @param viewType
     * @return custom new Firebase recipeAdapter to interface
     * with recycler
     */
    @NonNull
    @Override
    public recipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_recycler_item, parent, false);
        return new com.my.moms.pantry.recipeAdapter.recipeViewHolder(view);
    }

    /***
     *
     * subclass to create reference to the layout recipe_recycler_item.xml
     */
    public static class recipeViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView avatar;
        View mView;



        /***
         * custom view holder for recipe item
         * @param itemView hold the view
         */
        public recipeViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            name = itemView.findViewById(R.id.recipe_text);
            avatar = itemView.findViewById(R.id.recipe_avatar);
        }

        /***
         * toString() method for recipe model name
         * @return model name string
         */
        @Override
        public String toString() {
            return super.toString() + " '" + name.getText();
        }
    }

    /***
     * recipeAdapter constructor
     * @param options to customize the adapter
     */
    public recipeAdapter(@NonNull FirebaseRecyclerOptions<recipe> options) {
        super(options);
    }

    /***
     * Method to bind view to the layout with data from the recipe model class
     * @param holder holds the view
     * @param position tracks position
     * @param model recipe model to bind database elements
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onBindViewHolder(@NonNull recipeAdapter.recipeViewHolder holder,
                                    int position, @NonNull recipe model) {


        mContext = holder.mView.getContext();
        holder.name.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_animation));
        holder.mView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));

        holder.name.setText(WordUtils.capitalize(model.getName()));



        /***
         * On click event to pass firebase data from the viewholder to the RecipeDetailActivity
         * class. Binds the model data to the EXTRA_NAME3 variable in RecipeDetailActivity
         */
        holder.mView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, RecipeDetailActivity.class);
            Log.i(RecipeDetailActivity.EXTRA_NAME, "model.getName: " + model.getName());
            Log.i(RecipeDetailActivity.EXTRA_DESCRIPTION, "model.getdscription: " + model.getDescription());
            Log.i(RecipeDetailActivity.EXTRA_STEPS, "model.getQuantity: " + model.getSteps());
            Log.i(RecipeDetailActivity.EXTRA_SERVING, "model.getServingSize: " + model.getServing());
            Log.i(RecipeDetailActivity.EXTRA_INGREDIENTS, "model.getIngredients: " + model.getIngredients());
            Log.i(RecipeDetailActivity.EXTRA_DATE, "model.getIngredients: " + model.getDate());

        /* Send the database date to the Detail Activity through Intent */
            intent.putExtra(RecipeDetailActivity.EXTRA_NAME, model.getName());
            intent.putExtra(RecipeDetailActivity.EXTRA_DESCRIPTION, model.getDescription());
            intent.putExtra(RecipeDetailActivity.EXTRA_STEPS, model.getSteps());
            intent.putExtra(RecipeDetailActivity.EXTRA_INGREDIENTS, model.getIngredients());
            intent.putExtra(RecipeDetailActivity.EXTRA_SERVING, model.getServing());
            intent.putExtra(RecipeDetailActivity.EXTRA_DATE, model.getDate());



            context.startActivity(intent);
        });

        // set random avatar image
        RequestOptions options = new RequestOptions();
        Glide.with(holder.avatar.getContext())
                .load(recipe.getAvatar())
                .apply(options.fitCenter())
                .into(holder.avatar);

    }


}

