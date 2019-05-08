package com.simplefootballstatistic.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.simplefootballstatistic.ActivityMain;
import com.simplefootballstatistic.R;
import com.simplefootballstatistic.Views.ViewsActivityMain;

public class FragmentMain extends Fragment implements View.OnClickListener {
    ViewsActivityMain viewsActivityMain;
    private View view;

    public FragmentMain(){

    }

    // Method onCreateView dipanggil saat Fragment harus menampilkan layoutnya      // dengan membuat layout tersebut secara manual lewat objek View atau dengan     // membaca file XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Layout tampilan untuk fragment ini
        ActivityMain activityMain = (ActivityMain) getActivity();
        assert activityMain != null;
        this.viewsActivityMain = activityMain.getViews();
        view = inflater.inflate(R.layout.fragment_main, parent, false);
        return view;
    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau          // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        init();
    }

    private void init(){
        Button btn_register = view.findViewById(R.id.btn_register);
        Button btn_list_tim = view.findViewById(R.id.btn_list_tim);

        btn_list_tim.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:viewsActivityMain.pindah(1);
                break;
            case R.id.btn_list_tim:viewsActivityMain.pindah(2);
                break;
        }
    }
}
