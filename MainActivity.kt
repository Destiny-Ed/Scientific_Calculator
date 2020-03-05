package com.destiny.calc
//This app was created with JOY by Dikeocha Destiny through the GRACE of GOD

//app launched on 24/02/2020

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import android.widget.Toast
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlin.math.*


class MainActivity : AppCompatActivity() {


    //variable for admob
    private lateinit var mAdView: AdView
    private val mAppUnitId: String by lazy {

        "ca-app-pub-5***********28722~******187"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            //Now implementing the and initilizing the addmob to the app
            mAdView = findViewById(R.id.adView)

            initializeMobileAd(mAppUnitId)

            //Load banner ads
            loadBannerAd()

        //Display View and clear buttons
        val display = findViewById<TextView>(R.id.Result)
        val btnClearAll = findViewById<Button>(R.id.btnClear)
        val btnClear = findViewById<Button>(R.id.btnC)


        //btn for scientific calculator


        //Variable for all buttons operators
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val btnZero = findViewById<Button>(R.id.btnZero)
        val btnDot = findViewById<Button>(R.id.btnDot)

        //Variables for all buttons operands
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnDiv = findViewById<Button>(R.id.btnDiv)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSub = findViewById<Button>(R.id.btnSub)
        val btnEval = findViewById<Button>(R.id.btnEval)

        //Putting all operators in an array
        val buttonsOperators = arrayOf(btn1,btn2, btn3,btn4,btn5, btn6,btn7,btn8,btn9,btnZero)

        //Putting all operands in an array
        val buttonsOperands = arrayOf(btnMul, btnDiv, btnAdd)


        //All function and event listeners for horizontal view

        fun btnFunc(){
            //Looping through all buttonOperators and displaying their results
            for(button in buttonsOperators){
                button.setOnClickListener {
                    display.text = display.text.toString() + button.text
                }
            }
        }

        fun OperandFunc(){
            //Looping through all buttonOperand and displaying thier results
            for(button in buttonsOperands){
                button.setOnClickListener {
                    if(display.text == ""){
                        display.text = ""
                    }
                    else{
                        display.text = display.text.toString() + button.text
                    }
                }
            }
        }

        btnSub.setOnClickListener {
            display.text = display.text.toString() + btnSub.text
        }

        //Clear display
        btnClearAll.setOnClickListener {
            display.text = ""
        }

        //Remove the last number from the display
        btnClear.setOnClickListener {
            if(display.text == ""){
                return@setOnClickListener
            }
            else{
                display.text = display.text.substring(0, display.text.length - 1)
            }
        }

        //Add dot to the display
        btnDot.setOnClickListener {
            if (display.text == "" || display.text.contains("..")){
                return@setOnClickListener
            }
            else{
                display.text = display.text.toString() + btnDot.text
            }
        }

        //Evaluate display
        btnEval.setOnClickListener {
            try {
                if(display.text == "" || display.text.takeLast(1) == btnAdd.text ||
                    display.text.takeLast(1) == btnDiv.text || display.text.takeLast(1) == btnMul.text ||
                    display.text.takeLast(1) == btnSub.text){
                    return@setOnClickListener
                }
                else if(display.text.takeLast(2) == "/0"){
                    Toast.makeText(this@MainActivity, "Zero division error: Can't divide by zero", Toast.LENGTH_LONG).show()
                }
                else if (display.text.contains(".") || display.text.contains("/")){
                    //so if display content contains . evaluate with a decimal point
                    var Expression = ExpressionBuilder(display.text.toString()).build()
                    var result = Expression.evaluate()
                    display.text = result.toString()
                }
                else{
                    var Expression = ExpressionBuilder(display.text.toString()).build()
                    var result = Expression.evaluate()
                    //convert result to integer
                    val int = result.toInt()
                    display.text = int.toString()
//                display.text = display.text.substring(0, display.text.length - 2)
                }
            }
            catch (e: Exception){
                Toast.makeText(this, "INVALID INPUT", Toast.LENGTH_SHORT).show()
            }
        }

        //Evaluate display.text
        btnEq.setOnClickListener {
            try {
                if(display.text == "" || display.text.takeLast(1) == btnAdd.text ||
                    display.text.takeLast(1) == btnDiv.text || display.text.takeLast(1) == btnMul.text ||
                    display.text.takeLast(1) == btnSub.text){
                    return@setOnClickListener
                }
                else if(display.text.takeLast(2) == "/0"){
                    Toast.makeText(this@MainActivity, "Zero division error: Can't divide by zero", Toast.LENGTH_SHORT).show()
                }
                else if (display.text.contains(".") || display.text.contains("/")){
                    var Expression = ExpressionBuilder(display.text.toString()).build()
                    var result = Expression.evaluate()
                    display.text = result.toString()
                }
                else{
                    var Expression = ExpressionBuilder(display.text.toString()).build()
                    var result: Double = Expression.evaluate()
                    var int = result.toInt()
                    display.text = int.toString()
//                display.text = display.text.substring(0, display.text.length - 2)
                }
            }
            catch (e: Exception){
                Toast.makeText(this, "INVALID INPUT", Toast.LENGTH_SHORT).show()
            }
        }

        //Calling all functions
        btnFunc()
        OperandFunc()





        //All function and event listeners for horizontal view
        //Tan
        fun tann() {
            var btn = findViewById<Button>(R.id.Tan)
            btn.setOnClickListener {
                try {
                    if(display.text == ""){
                        Toast.makeText(this@MainActivity, "No Valid Input", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    else{
                        var x = tan(display.text.toString().toDouble())
                        display.text = x.toString()
                    }
                }
                catch (e: Exception){
                    Toast.makeText(this, "INVALID INPUT", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //All function and event listeners for horizontal view
        //Sin
        fun sinn() {
            var btn = findViewById<Button>(R.id.Sin)
            btn.setOnClickListener {
                try {
                    if(display.text == ""){
                        Toast.makeText(this@MainActivity, "No Valid Input", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    else{
                        var x = sin(display.text.toString().toDouble())
                        display.text = x.toString()
                    }
                }
                catch (E: Exception){
                    Toast.makeText(this, "INVALID INPUT", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //All function and event listeners for horizontal view
        //log
        fun Logg() {
            var btn = findViewById<Button>(R.id.Log)
            btn.setOnClickListener {

                try {
                    if(display.text == ""){
                        Toast.makeText(this@MainActivity, "No Valid Input", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    else{
                        var x = log(display.text.toString().toDouble(), Double.MAX_VALUE)
                        display.text = x.toString()
                    }
                }
                catch (e: Exception){
                    Toast.makeText(this, "INVALID INPUT", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun coss() {
            var btn = findViewById<Button>(R.id.btnCos)
            btn.setOnClickListener {
                try {
                    if (display.text == "") {
                        Toast.makeText(this, "No Valid Input", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    else {
                        var x = cos(display.text.toString().toDouble())
                        display.text = x.toString()
                    }
                }
                catch (E: Exception){
                    Toast.makeText(this, "INVALID INPUT", Toast.LENGTH_SHORT).show()
                }
            }

        }

        //All function and event listeners for horizontal view
        //Calling all function
        coss()
        tann()
        sinn()
        Logg()



        //Save the text in the display when screen orientation changes
        if(savedInstanceState != null){
            var message = savedInstanceState.get("message")
            display.text = message.toString()
        }




    }

    private fun loadBannerAd() {
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun initializeMobileAd(appUnitId: String) {
        MobileAds.initialize(this, appUnitId)

    }

    //Save the text in the display when screen orientation changes

    public override fun onSaveInstanceState(outState: Bundle) {
        var display = findViewById<TextView>(R.id.Result)
        outState.putString("message", display.text.toString())
        super.onSaveInstanceState(outState)
    }

    //share app to various available app
    //create a share function
    private fun share() {
        //intent declaration
        var intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        //get the app link or uri
        var uri = "Download scientific calculator using this link " + " https://play.google.com/store/apps/details?id=com.destiny.calc"
        //put uri into an extra
        intent.putExtra(Intent.EXTRA_TEXT, uri)
        //start intent activity and show apps
        startActivity(Intent.createChooser(intent, "share app via"))
    }

    //Show menu items
    //Create menuOptions and inflate menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //set onClick Listener to all menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> share()
            R.id.about -> startActivity(Intent(this, About::class.java))
        }

        return super.onOptionsItemSelected(item)

    }






}


//This app is done by Dikeocha Destiny and it has been completed successfully
