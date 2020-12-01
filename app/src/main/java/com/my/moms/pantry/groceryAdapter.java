package com.my.moms.pantry;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


/***
 *Custom FirebaseRecyclerAdapter
 *
 * Firebase Recycler Adapter is a class from firebase UI
 * to provide the methods to bind, change and display the
 * contents of a firebase database in a recycler view
 */
class groceryAdapter extends FirebaseRecyclerAdapter<grocery, groceryAdapter.groceryViewHolder> {

    /***
     *
     * subclass to create reference to the layout pantry_recycler_item.xml
     */
    public static class groceryViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView avatar;
        public View mView;


        /***
         * custom view holder for grocery item
         * @param itemView hold the view
         */
        public groceryViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            name = itemView.findViewById(R.id.text1);
            avatar = itemView.findViewById(R.id.avatar);
        }

        /***
         * toString() method for grocery model name
         * @return model name string
         */
        @Override
        public String toString() {
            return super.toString() + " '" + name.getText();
        }
    }

    /***
     * groceryAdapter constructor
     * @param options to customize the adapter
     */
    public groceryAdapter(@NonNull FirebaseRecyclerOptions<grocery> options) {
        super(options);
    }

    /***
     * Method to bind view to the layout with data from the grocery model class
     * @param holder holds the view
     * @param position tracks position
     * @param model grocery model to bind database elements
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onBindViewHolder(@NonNull groceryAdapter.groceryViewHolder holder,
                                    int position, @NonNull grocery model) {


        holder.name.setText(model.getName());


        Log.i(model.getName(), "name: position " + position);
        Log.i(model.getLifecycle(), "quantty: position " + position);
        Log.i(model.getQuantity(), "lifecycle: position " + position);

        /***
         * On click event to pass firebase data from the viewholder to the PantryDetailActivity
         * class. Binds the model data to the EXTRA_NAME variable in PantryDetailActivity
         */
        holder.mView.setOnClickListener(v -> {
            Context context = v.getContext();
            Intent intent = new Intent(context, GroceryListDetailActivity.class);
            Log.i(PantryDetailActivity.EXTRA_NAME, "model.getName: " + model.getName());
            Log.i(PantryDetailActivity.EXTRA_LIFECYCLE, "model.getLifecycle: " + model.getLifecycle());
            Log.i(PantryDetailActivity.EXTRA_QUANTITY, "model.getQuantity: " + model.getQuantity());
            Log.i(PantryDetailActivity.EXTRA_DATE, "model.getDate: " + model.getDateAdded());


            /* Send the database date to the Detail Activity through Intent */
            intent.putExtra(PantryDetailActivity.EXTRA_NAME, model.getName());
            intent.putExtra(PantryDetailActivity.EXTRA_QUANTITY, model.getQuantity());
            intent.putExtra(PantryDetailActivity.EXTRA_LIFECYCLE, model.getLifecycle());
            intent.putExtra(PantryDetailActivity.EXTRA_DATE, model.getDateAdded());

            context.startActivity(intent);
        });

        // set random avatar image
        RequestOptions options = new RequestOptions();
        Glide.with(holder.avatar.getContext())
                .load(grocery.getRandFoodImage())
                .apply(options.fitCenter())
                .into(holder.avatar);

    }

    /***
     * method that tells the class which layout
     * @param parent
     * @param viewType
     * @return custom new Firebase groceryAdapter to interface
     * with recycler
     */
    @NonNull
    @Override
    public com.my.moms.pantry.groceryAdapter.groceryViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grocery_recycler_item, parent, false);
        return new com.my.moms.pantry.groceryAdapter.groceryViewHolder(view);
    }
}


///***
// *Custom FirebaseRecyclerAdapter
// *
// * Firebase Recycler Adapter is a class from firebase UI
// * to provide the methods to bind, change and display the
// * contents of a firebase database in a recycler view
// */
//
//public class groceryAdapter extends FirebaseRecyclerAdapter<pantry_recycler_item, groceryAdapter.groceryViewHolder> {
//
//    /***
//     * groceryAdapter constructor
//     * @param options to customize the adapter
//     */
//    public groceryAdapter(
//            @NonNull FirebaseRecyclerOptions<pantry_recycler_item> options) {
//        super(options);
//    }
//
//    // Function to bind the view in Card view(here
//    // "pantry_recycler_item.xml") with data in
//    // model class(here "pantry_recycler_item.class")
////    could potentially be a problem because i used the linear layout and not cardview
//
//    /***
//     * Method to bind view to the layout
//     * @param holder holds the view
//     * @param position tracks position
//     * @param model pantry_recycler_item model to bind detabase elements
//     */
//    @Override
//    protected void
//    onBindViewHolder(@NonNull groceryViewHolder holder,
//                     int position, @NonNull pantry_recycler_item model) {
//
//        // Add firstname from model class (here
//        // "pantry_recycler_item.class")to appropriate view in Card
//        // view (here "pantry_recycler_item.xml")
//
//        // item name
//        holder.name.setText(model.getName());
////        holder.mView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Context context = v.getContext();
////                Intent intent = new Intent(context, PantryDetailActivity.class);
////                intent.putExtra(PantryDetailActivity.EXTRA_NAME, holder.mBoundString);
////
////                context.startActivity(intent);
////            }
////        });
//
//        // avatar image
//        RequestOptions options = new RequestOptions();
//        Glide.with(holder.avatar.getContext())
//                .load(pantry_recycler_item.getRandFoodImage())
//                .apply(options.fitCenter())
//                .into(holder.avatar);
//
//
////        // Add lastname from model class (here
////        // "pantry_recycler_item.class")to appropriate view in Card
////        // view (here "pantry_recycler_item.xml")
////        holder.lastname.setText(model.getName());
////
////        // Add age from model class (here
////        // "pantry_recycler_item.class")to appropriate view in Card
////        // view (here "pantry_recycler_item.xml")
////        holder.age.setText(model.getQuantity());
////
////        // add lifecycle from model class
////        //"pantry_recycler_item.class")to appropriate view in Card
////        // view (here "pantry_recycler_item.xml")
////        holder.age.setText(model.getLifecycle());
//    }
//
//    // Function to tell the class about the Card view (here
//    // "pantry_recycler_item.xml")in
//    // which the data will be shown
//
//    /***
//     * method that tells the classs which layout
//     * @param parent
//     * @param viewType
//     * @return custom new Firebase groceryAdapter to interface
//     * with recycler
//     */
//    @NonNull
//    @Override
//    public groceryViewHolder
//    onCreateViewHolder(@NonNull ViewGroup parent,
//                       int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.pantry_recycler_item, parent, false);
//        return new groceryAdapter.groceryViewHolder(view);
//    }
//
//    // Sub Class to create references of the views in Crad
//    // view (here "pantry_recycler_item.xml")
//
//    /***
//     *
//     * subclass to create referece to the layout pantry_recycler_item.xml
//     */
//    static class groceryViewHolder extends RecyclerView.ViewHolder {
//        TextView name;
//        ImageView avatar;
//
//        public groceryViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            name = itemView.findViewById(R.id.text1);
//            avatar = itemView.findViewById(R.id.avatar);
//
//
//        }
//    }
//
//}


///***
// *Custom FirebaseRecyclerAdapter
// *
// * Firebase Recycler Adapter is a class from firebase UI
// * to provide the methods to bind, change and display the
// * contents of a firebase database in a recycler view
// */
//
//public class groceryAdapter extends FirebaseRecyclerAdapter<pantry_recycler_item, groceryAdapter.groceryViewHolder> {
//
//    /***
//     * groceryAdapter constructor
//     * @param options to customize the adapter
//     */
//    public groceryAdapter(
//            @NonNull FirebaseRecyclerOptions<pantry_recycler_item> options) {
//        super(options);
//    }
//
//    // Function to bind the view in Card view(here
//    // "pantry_recycler_item.xml") with data in
//    // model class(here "pantry_recycler_item.class")
////    could potentially be a problem because i used the linear layout and not cardview
//
//    /***
//     * Method to bind view to the layout
//     * @param holder holds the view
//     * @param position tracks position
//     * @param model pantry_recycler_item model to bind detabase elements
//     */
//    @Override
//    protected void
//    onBindViewHolder(@NonNull groceryViewHolder holder,
//                     int position, @NonNull pantry_recycler_item model) {
//
//        // Add firstname from model class (here
//        // "pantry_recycler_item.class")to appropriate view in Card
//        // view (here "pantry_recycler_item.xml")
//
//        // item name
//        holder.name.setText(model.getName());
////        holder.mView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Context context = v.getContext();
////                Intent intent = new Intent(context, PantryDetailActivity.class);
////                intent.putExtra(PantryDetailActivity.EXTRA_NAME, holder.mBoundString);
////
////                context.startActivity(intent);
////            }
////        });
//
//        // avatar image
//        RequestOptions options = new RequestOptions();
//        Glide.with(holder.avatar.getContext())
//                .load(pantry_recycler_item.getRandFoodImage())
//                .apply(options.fitCenter())
//                .into(holder.avatar);
//
//
////        // Add lastname from model class (here
////        // "pantry_recycler_item.class")to appropriate view in Card
////        // view (here "pantry_recycler_item.xml")
////        holder.lastname.setText(model.getName());
////
////        // Add age from model class (here
////        // "pantry_recycler_item.class")to appropriate view in Card
////        // view (here "pantry_recycler_item.xml")
////        holder.age.setText(model.getQuantity());
////
////        // add lifecycle from model class
////        //"pantry_recycler_item.class")to appropriate view in Card
////        // view (here "pantry_recycler_item.xml")
////        holder.age.setText(model.getLifecycle());
//    }
//
//    // Function to tell the class about the Card view (here
//    // "pantry_recycler_item.xml")in
//    // which the data will be shown
//
//    /***
//     * method that tells the classs which layout
//     * @param parent
//     * @param viewType
//     * @return custom new Firebase groceryAdapter to interface
//     * with recycler
//     */
//    @NonNull
//    @Override
//    public groceryViewHolder
//    onCreateViewHolder(@NonNull ViewGroup parent,
//                       int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.pantry_recycler_item, parent, false);
//        return new groceryAdapter.groceryViewHolder(view);
//    }
//
//    // Sub Class to create references of the views in Crad
//    // view (here "pantry_recycler_item.xml")
//
//    /***
//     *
//     * subclass to create referece to the layout pantry_recycler_item.xml
//     */
//    static class groceryViewHolder extends RecyclerView.ViewHolder {
//        TextView name;
//        ImageView avatar;
//
//        public groceryViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            name = itemView.findViewById(R.id.text1);
//            avatar = itemView.findViewById(R.id.avatar);
//
//
//        }
//    }
//
//}