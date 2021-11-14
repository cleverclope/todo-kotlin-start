package clever.arinda.todo

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class TodoAdapter(

    private val todos: MutableList<Todo>

) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo, parent,
                false

            )
        )


    }

    fun addToDo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size -1)
    }

    fun deleteDoneTodos(){
        todos.removeAll{todo->
        todo.ifChecked
         }
    }

    private fun toggleStrikeThrough(title : TextView, ifChecked:Boolean){
        if (ifChecked){
            title.paintFlags = title.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            title.paintFlags = title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }


    }


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]

        holder.itemView.apply {

            val title = findViewById<TextView>(R.id.title)
            val checkDone = findViewById<CheckBox>(R.id.checkDone)

            title.text = curTodo.tittle
            checkDone.isChecked = curTodo.ifChecked

            toggleStrikeThrough(title, curTodo.ifChecked)
            checkDone.setOnCheckedChangeListener{buttonView, ifChecked ->
                toggleStrikeThrough(title, ifChecked)
                curTodo.ifChecked = !curTodo.ifChecked
            }

        }


    }

    override fun getItemCount(): Int {
        return todos.size
    }
}