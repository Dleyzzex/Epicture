package com.example.epicture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var identifiant_componant = view.findViewById<EditText>(R.id.identifiant);
        var password_componant = view.findViewById<EditText>(R.id.password);
        var id = "";
        var pass = "";

        // change view if button clicked
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            id = identifiant_componant.toString();
            pass = password_componant.toString();
        }
        identifiant_componant.setOnClickListener {
            identifiant_componant.setText("");
        }
        password_componant.setOnClickListener {
            password_componant.setText("");
        }
    }
}