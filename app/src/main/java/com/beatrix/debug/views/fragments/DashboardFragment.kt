package com.beatrix.debug.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.beatrix.debug.views.ExchangeActivity
import com.beatrix.debug.R
import com.beatrix.debug.views.ExpensesActivity

// Here the main screen of the app is represented
// TO BE EXPANDED
class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // below is definition of all working buttons which send the user to the new screen
        // depending on what button they clicked.
        val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val buttonExpenses = view.findViewById<Button>(R.id.buttonExpenses)
        val buttonCurrency = view.findViewById<Button>(R.id.buttonCurrency)

        buttonExpenses.setOnClickListener {
            val intent = Intent(activity, ExpensesActivity::class.java)
            startActivity(intent)
        }

        buttonCurrency.setOnClickListener {
            val intent = Intent(activity, ExchangeActivity::class.java)
            startActivity(intent)
        }
        // Inflate the layout for this fragment
        return view
    }

}