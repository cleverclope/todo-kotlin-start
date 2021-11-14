package clever.arinda.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvtodoIte = findViewById<RecyclerView>(R.id.rvtodoItem)
        val edTodoItem = findViewById<EditText>(R.id.edTodoItem)
        val addTod = findViewById<Button>(R.id.addTodo)
        val delTod = findViewById<Button>(R.id.delTodo)


        todoAdapter = TodoAdapter(mutableListOf())
        rvtodoIte.adapter = todoAdapter
        rvtodoIte.layoutManager = LinearLayoutManager(this)

        addTod.setOnClickListener {
            val todoTitleCaptured = edTodoItem.text.toString()
            if (todoTitleCaptured.isNotEmpty()) {
                val todo = Todo(todoTitleCaptured)
                todoAdapter.addToDo(todo)
                edTodoItem.text.clear()
            }

        }

        delTod.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }

    }
}