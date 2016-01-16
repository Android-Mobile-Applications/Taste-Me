package com.example.nitro.tasteme.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.nitro.tasteme.R;

import static com.example.nitro.tasteme.R.id.etShoppingItemTitle;


/**
 * Created by Nitro on 2016-01-08.
 */
public class ShoppingCartFragment extends Fragment {


    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        listView = (ListView) rootView.findViewById(R.id.lvShoppingCart);
        adapter = new ArrayAdapter<String>(getContext(), R.layout.shopping_item, etShoppingItemTitle);
        listView.setAdapter(adapter);

        Button buttonAddItem = (Button) rootView.findViewById(R.id.btnCreateItem);
        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapter.add("pesho");
            }
        });

        Button buttonClearAll = (Button) rootView.findViewById(R.id.btnClear);
        buttonClearAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                adapter.clear();
            }
        });

        return rootView;
    }


}
