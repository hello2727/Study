package org.techtown.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFragment extends Fragment {

    //두 개 파일의 연결(메인 프래그먼트.xml을 메인 프래그먼트.java로 올려줌)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity(); //내가 올라가있는 액티비티 참조
                activity.onFragmentChanged(1); //1이면 메인프래그먼트, 0이면 메뉴프래그먼트 띄어줌
            }
        });

        return rootView;
    }

}
