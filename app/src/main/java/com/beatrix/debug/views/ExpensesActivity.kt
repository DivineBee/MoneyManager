package com.beatrix.debug.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.beatrix.debug.*
import com.beatrix.debug.databinding.ActivityExpensesBinding
import com.beatrix.debug.utils.Expenses
import com.beatrix.debug.utils.ExpensesAdapter
import com.beatrix.debug.utils.OnExpensesDetailsClickListener
import kotlinx.android.synthetic.main.activity_expenses.*

/**
 * This class is responsible for the recycler view which holds the list of
 * expenses which user has made.
 */
class ExpensesActivity : AppCompatActivity(), OnExpensesDetailsClickListener {
    // declare binding
    lateinit var binding: ActivityExpensesBinding
    // declaration of array to hold the expenses
    lateinit var expensesList: ArrayList<Expenses>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_expenses)

        // array initialization
        expensesList = ArrayList()

        // calling the function for array population with data
        addExpenses()

        // Set the layout manager that this RecyclerView will use.
        expensesRecycler.layoutManager = LinearLayoutManager(this)
        // add special layout offset to item views from adapter's data set
        expensesRecycler.addItemDecoration(DividerItemDecoration(this, 1))
        // Set a new adapter to provide child views on demand.
        expensesRecycler.adapter = ExpensesAdapter(expensesList, this)
    }

    // populate the list with dummy data, in future will be implemented dynamic add from the user
    fun addExpenses(){
        expensesList.add(Expenses("FOOD", 300.50, "Bought food for a week", R.drawable.food))
        expensesList.add(Expenses("CLOTHES", 900.24, "New dress and a belt", R.drawable.clothes))
        expensesList.add(Expenses("BEAUTY", 340.00, "Vichy cream", R.drawable.beauty))
        expensesList.add(Expenses("BILLS", 140.50, "Paid for water and gas", R.drawable.bills))
        expensesList.add(Expenses("CAR", 500.00, "Fueled the car", R.drawable.car))
        expensesList.add(Expenses("DETERGENTS", 235.45, "Detergents for clothes", R.drawable.detergents))
    }

    // on item click bring user to the details screen where he will see the details about product
    override fun onItemClick(item: Expenses, position: Int) {
        var intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("AMOUNT", item.amount.toString())
        intent.putExtra("CATEGORY", item.category)
        intent.putExtra("CATEGLOGO", item.categoryImage.toString())
        intent.putExtra("DESCRIPTION", item.description)
        startActivity(intent)
    }
}