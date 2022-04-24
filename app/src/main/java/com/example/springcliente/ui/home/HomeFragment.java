package com.example.springcliente.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.springcliente.Entidades.Estudiante;
import com.example.springcliente.R;
import com.example.springcliente.conexion.Centralizador;
import com.example.springcliente.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private TextView textView;
    private Button btnenviar, btnregistrar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);//binding.getRoot();

        textView = root.findViewById(R.id.text_home);
        btnenviar = root.findViewById(R.id.btnenviar);
        btnregistrar = root.findViewById(R.id.btnenviar);
        btnenviar.setOnClickListener(x -> {//observe el header en el Request
            Centralizador.getEstudiantes(getActivity(), getContext());
        });
        btnregistrar.setOnClickListener(x -> {//observe el header en el Request
            Estudiante e = new Estudiante();
            e.id = 888;
            e.nombre = "XXX";
            e.apellido = "YYY";
            Centralizador.crearestudiante(getActivity(), getContext(), e);
        });
        textView.setText("Hola a todos");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}