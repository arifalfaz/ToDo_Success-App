package com.elecsiya.todo_success;



import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText taskName, textDesc, taskDeadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskName = findViewById(R.id.editTextTask);
        textDesc = findViewById(R.id.editTextDesc);
        taskDeadline = findViewById(R.id.editTextFinishBy);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        final String sTask = taskName.getText().toString().trim();
        final String sDesc = textDesc.getText().toString().trim();
        final String sFinishBy = taskDeadline.getText().toString().trim();

        if (sTask.isEmpty()) {
            taskName.setError("Task required");
            taskName.requestFocus();
            return;
        }

        if (sDesc.isEmpty()) {
            textDesc.setError("Task Steps required");
            textDesc.requestFocus();
            return;
        }

        if (sFinishBy.isEmpty()) {
            taskDeadline.setError("Task Deadline required");
            taskDeadline.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task task = new Task();
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(false);

                //adding to database
                com.elecsiya.todo_success.DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Congratulations! your work has been saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddTaskActivity.this,MainActivity.class));
        AddTaskActivity.super.onBackPressed();
    }
}
