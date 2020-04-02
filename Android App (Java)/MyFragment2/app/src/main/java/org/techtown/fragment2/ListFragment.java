package org.techtown.fragment2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {
    ImageSelectionCallback callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof ImageSelectionCallback) { //"Image~함수 구현하는 것이 맞다면"
            callback = (ImageSelectionCallback) context; //액티비티가 callback변수에 들어감
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인액티비티에서 직접 구현하는것에서 인터페이스로 진화한 구현
                if (callback != null) {
                    callback.onImageSelected(0);
                }
            }
        });

        Button button2 = rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인액티비티에서 직접 구현하는것에서 인터페이스로 진화한 구현
                if (callback != null) {
                    callback.onImageSelected(1);
                }
            }
        });

        Button button3 = rootView.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인액티비티에서 직접 구현하는것에서 인터페이스로 진화한 구현
                if (callback != null) {
                    callback.onImageSelected(2);
                }
            }
        });

        return rootView;
    }
}
