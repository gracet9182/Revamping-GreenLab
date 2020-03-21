package com.example.green.ui.recycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.green.R;

public class RecycleFragment extends Fragment {
    private RecycleViewModel recycleViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recycleViewModel = ViewModelProviders.of(this).get(RecycleViewModel.class);
        root = inflater.inflate(R.layout.fragment_recycle, container, false);
        Button button = root.findViewById(R.id.search_btn);
        ImageView imageView = root.findViewById(R.id.itemPic);
        TextView textview = root.findViewById(R.id.recyclingLabel);
        imageView.setImageResource(R.drawable.glass_bottle);
        textview.setText("Name: Glass Bottle \nRecyclable: Yes \nType: Glass");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText search = root.findViewById(R.id.search_recycle);
                String search_text = search.getText().toString().toLowerCase();
                initView(root, search_text);
            }
        });
        return root;
    }

    private void initView(View root, String search_text) {
        ImageView imageView = root.findViewById(R.id.itemPic);
        TextView textview = root.findViewById(R.id.recyclingLabel);
        switch(search_text){
            case "plastic bottle":
                imageView.setImageResource(R.drawable.plastic_bottle);
                textview.setText("Name: Plastic Bottle \nRecyclable: Yes \nType: Plastic");
                break;
            case "glass bottle":
                imageView.setImageResource(R.drawable.glass_bottle);
                textview.setText("Name: Glass Bottle \nRecyclable: Yes \nType: Glass");
                break;
            case "metal can":
                imageView.setImageResource(R.drawable.aluminum_can);
                textview.setText("Name: Metal Can \nRecyclable: Yes \nType: Metal");
                break;
            case "clothes":
                imageView.setImageResource(R.drawable.clothes);
                textview.setText("Name: Clothes \nRecyclable: Yes \nType: Textile");
                break;
            case "paper":
                imageView.setImageResource(R.drawable.paper);
                textview.setText("Name: Papers \nRecyclable: Yes \nType: Paper");
                break;
            default:
                imageView.setVisibility(View.INVISIBLE);
                textview.setText("Sorry, our database doesn't have what you are trying to searching " +
                        "for. Please go back to the previous page.");
                break;
        }
    }
}
