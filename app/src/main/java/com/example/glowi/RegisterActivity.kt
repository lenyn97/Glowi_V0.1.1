package com.example.glowi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var buttonRegister: Button
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPhone = findViewById(R.id.editTextPhone)
        buttonRegister = findViewById(R.id.buttonRegister)//boton registro
        databaseHelper = DatabaseHelper(this)

        buttonRegister.setOnClickListener {
            val name = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val phone = editTextPhone.text.toString()

            val checkBoxTerms: CheckBox = findViewById(R.id.checkBoxTerms)
            val continueButton: Button = findViewById(R.id.buttonRegister)
            val insertedId = databaseHelper.insertUser(name, email, phone)

            // Contructor para verificar el estado del checkBox
            checkBoxTerms.setOnCheckedChangeListener { _, isChecked ->
                continueButton.isEnabled = isChecked
            }
            //Creamos la funcion de chequeo para continuar

            continueButton.setOnClickListener {
                if (checkBoxTerms.isChecked) {

                    // Iniciamos la activity Catedras
                    val intent = Intent(this, MContenidoActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(
                        this,
                        "Para continuar acepte los términos y condiciones.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
            //Registro de usuarios
            if (insertedId != -1L) {
                Toast.makeText(this, "Usuario registrado con ID: $insertedId", Toast.LENGTH_SHORT).show()
                clearFields()
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
            }
            //Creamos un condicionante para enviar saludo al usuario que ingresa
            if (name.isNotEmpty()) {
                val welcomeMessage = "¡Bienvenido, $name!"
                // Mostrar el mensaje de bienvenida (puedes usar un Toast, TextView, etc.)
                // Por ejemplo, mostrando un Toast:
                Toast.makeText(this, welcomeMessage, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, ingrese su nombre.", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun clearFields() {
        editTextName.text.clear()
        editTextEmail.text.clear()
        editTextPhone.text.clear()
    }




}

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val TABLE_NAME = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_EMAIL TEXT, $COLUMN_PHONE TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertUser(name: String, email: String, phone: String): Long {
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, name)
        contentValues.put(COLUMN_EMAIL, email)
        contentValues.put(COLUMN_PHONE, phone)

        val db = writableDatabase
        return db.insert(TABLE_NAME, null, contentValues)
    }
}

