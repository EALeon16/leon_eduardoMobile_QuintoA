package com.example.leon_eduardo.vista.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.modelo.Artista;
import com.example.leon_eduardo.vista.adapter.Artista_adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoMI.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoMI#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoMI extends Fragment implements View.OnClickListener {
    Button botonGuardar, botonBT;
    EditText cajaNombres, cajaApellidos, cajaNombreA;
    TextView datos;
    RecyclerView recyclerViewMI;
    List<Artista> listaArtistas;
    Artista_adapter adapter;
    String lineas;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentoMI() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoMI.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoMI newInstance(String param1, String param2) {
        FragmentoMI fragment = new FragmentoMI();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragmento_mi, container, false);
        botonGuardar = vista.findViewById(R.id.btnGuardarMIM);
        botonBT = vista.findViewById(R.id.btnBuscarTodosMIM);
        cajaNombres = vista.findViewById(R.id.txtNombresMIM);
        cajaApellidos = vista.findViewById(R.id.txtApellidosMIM);
        cajaNombreA = vista.findViewById(R.id.txtNombreAMIM);
        recyclerViewMI = vista.findViewById(R.id.recyclerMIM);
        datos = vista.findViewById(R.id.txtVerM);
        botonGuardar.setOnClickListener(this);
        botonBT.setOnClickListener(this);
        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGuardarMIM:
                try{
                    OutputStreamWriter escritor = new OutputStreamWriter(getActivity().openFileOutput("artistastex.txt", Context.MODE_APPEND));
                    escritor.write(cajaNombres.getText().toString()+ "," + cajaApellidos.getText().toString() +"," + cajaNombreA.getText().toString() + ";");
                    escritor.close();

                }catch (Exception ex){
                    Log.e("ArchivoMI", "Error de escritura" + ex.getMessage());

                }
                break;
            case R.id.btnBuscarTodosMIM:
                try{
                    BufferedReader lector = new BufferedReader(new InputStreamReader(getActivity().openFileInput("artistastex.txt")));
                    lineas = lector.readLine();
                    lector.close();
                    cargar_recicler();


                }catch (Exception ex){
                    Log.e("ArchivoMI", "Error de escritura" + ex.getMessage());

                }
                break;
        }

    }
    public void cargar_recicler(){
        listaArtistas = new ArrayList<Artista>();
        String[] listaAI;
        String[] listaA =  lineas.split(";");

        for(int i=0; i < listaA.length; i++){
            Artista artista = new Artista();
            listaAI = listaA[i].split(",");
            artista.setNombres(listaAI[0]);
            artista.setApellidos(listaAI[1]);
            artista.setNombreArtistico(listaAI[2]);
            listaArtistas.add(artista);
        }
        adapter = new Artista_adapter(listaArtistas);
        recyclerViewMI.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMI.setAdapter(adapter);



    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
