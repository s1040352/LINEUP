package com.example.a1234.lineupall;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentOne extends android.app.Fragment  {
    private Button store01;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_one,container,false);
        store01 = (Button) view.findViewById(R.id.store01);
        store01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.store01:
                        Intent intent = new Intent(getActivity(),Store11.class);
                        startActivity(intent);
                        break;



                }
            }

        });
        return view;
    }

}