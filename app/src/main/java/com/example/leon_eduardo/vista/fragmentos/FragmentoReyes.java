package com.example.leon_eduardo.vista.fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leon_eduardo.R;
import com.example.leon_eduardo.vista.adapter.Artista_adapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentoReyes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentoReyes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoReyes extends Fragment {

    TextView datos;
    RecyclerView recycler;

    Artista_adapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentoReyes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoReyes.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoReyes newInstance(String param1, String param2) {
        FragmentoReyes fragment = new FragmentoReyes();
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
        View vista = inflater.inflate(R.layout.fragment_fragmento_reyes, container, false);
        datos = vista.findViewById(R.id.txtReyesM);
        recycler = vista.findViewById(R.id.actividadReyesM);
        obtenerReyes();
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

    private  void obtenerReyes(){
        try{
            DocumentBuilder bilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = bilder.parse(getResources().openRawResource(R.raw.reyes), null);
            NodeList listagodos = doc.getElementsByTagName("godo");

            Toast.makeText(getContext(), "numero de reyes  " + listagodos.getLength(), Toast.LENGTH_SHORT).show();
            datos.setText("numero de reyes" + listagodos.getLength());
            String result = "";
            for(int i = 0; i < listagodos.getLength(); i ++){

                Node Reyes = listagodos.item(i);
                Element element2 =  (Element) Reyes;
                datos.setText(datos.getText()+"\nNombre: "+ getValue("nombre", element2)+"\n");
                datos.setText(datos.getText()+"\nPeriodo: "+ getValue("periodo", element2)+"\n");



            }


        }catch(Exception ex){

        }
    }

    private static String getValue(String tag, Element element){
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();

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
