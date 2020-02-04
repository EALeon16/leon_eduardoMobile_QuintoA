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
import android.widget.Toast;

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.modelo.Artista;
import com.example.leon_eduardo.vista.adapter.Artista_adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoArtistaRecycler.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoArtistaRecycler#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoArtistaRecycler extends Fragment {
    RecyclerView recyclerViewArtistas;
    Artista_adapter adapter;
    List<Artista> listaArtistas;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentoArtistaRecycler() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoArtistaRecycler.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoArtistaRecycler newInstance(String param1, String param2) {
        FragmentoArtistaRecycler fragment = new FragmentoArtistaRecycler();
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
        View vista = inflater.inflate(R.layout.fragment_fragmento_artista_recycler, container, false);
        recyclerViewArtistas = vista.findViewById(R.id.recyclerArtistaM);
        cargarRecycler();
        return  vista;
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

    private void cargarRecycler(){


        Artista artista1 = new Artista();
        artista1.setNombres("Luis ");
        artista1.setApellidos(" Miguel ");
        artista1.setNombreArtistico(" Luismi");
        artista1.setFoto(R.drawable.luis);
        artista1.setFechaNacimiento("12-10-1965");

        Artista artista2 = new Artista();
        artista2.setNombres(" Don");
        artista2.setApellidos(" Merardo ");
        artista2.setNombreArtistico(" Donme");
        artista2.setFoto(R.drawable.merardo);
        artista2.setFechaNacimiento("01-05-1962");






        listaArtistas = new ArrayList<Artista>();

        listaArtistas.add(artista1);
        listaArtistas.add(artista2);

        adapter = new Artista_adapter(listaArtistas);
        recyclerViewArtistas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setOnClick((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = listaArtistas.get(recyclerViewArtistas.getChildAdapterPosition(v)).getFechaNacimiento();
                Log.e("codigo",""+fecha);
                Toast.makeText(getContext(), "La fecha de nacimiento es: " + fecha, Toast.LENGTH_SHORT).show();

            }
        }));
        recyclerViewArtistas.setAdapter(adapter);

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
