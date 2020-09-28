package com.colorwheelapp.colourapp

import android.graphics.Color
import android.graphics.Color.parseColor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.graphics.blue
import androidx.core.graphics.toColorInt
import com.apandroid.colorwheel.ColorWheel
import com.apandroid.colorwheel.gradientseekbar.GradientSeekBar
import com.apandroid.colorwheel.gradientseekbar.currentColorAlpha
import com.apandroid.colorwheel.gradientseekbar.setBlackToColor

import com.wefika.horizontalpicker.HorizontalPicker
import dev.jorgecastillo.androidcolorx.library.*


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), HorizontalPicker.OnItemSelected, HorizontalPicker.OnItemClicked  {
    companion object {
        var i = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val colorWheel = findViewById<ColorWheel>(R.id.colorWheel)
        val slider = gradientSeekBar.argb

        val FirstLocator = findViewById<ImageView>(R.id.GridCol1)
        val SecondLocator = findViewById<ImageView>(R.id.GridCol2)
        val FirstTextLocator = findViewById<TextView>(R.id.gridcol1_overlay)
        var SecondTextLocator = findViewById<TextView>(R.id.gridcol2_overlay)
        var ThirdLocator = findViewById<ImageView>(R.id.GridCol3)
        val ThirdTextLocator = findViewById<TextView>(R.id.gridCol3_overlay)
        val FourthLocator = findViewById<ImageView>(R.id.GridCol4)
        var FourthTextLocator = findViewById<TextView>(R.id.GridCol4_overlay)

        val chosenColour = findViewById<TextView>(R.id.chosen_colour_value)
        val chosenColourrgb = findViewById<TextView>(R.id.chosen_colour_value_rgb)

        FirstLocator.setColorFilter(R.color.black)
        SecondLocator.setColorFilter(R.color.black)
        ThirdLocator.setColorFilter(R.color.black)
        FourthLocator.setColorFilter(R.color.black)

        var picker = HorizontalPicker(this)
        picker = findViewById<HorizontalPicker>(R.id.relationship_selector)




        var background = findViewById<View>(R.id.background)

//BIG SETTER FUNCTION

        fun setter(argb: Int, i: Int, rgb: Int) {

            chosen_colour.setColorFilter(argb)
            chosenColourrgb.text = argb.asRgb().toString()

            var hash = getString(R.string.hash)
            var hexx = argb.asHex().toString().substring(3)
            chosenColour.text = hash + hexx





            var comp = argb.complimentary() //returns one complimentary, will be at array 1
            var analogous: Pair<Int, Int> = argb.analogous() //returns two analogous, will be at array 3
            var tri: Pair<Int, Int> = argb.triadic() //returns two which make triadic combined with the comp, will be ar array2
            var tet: Triple<Int, Int, Int> = argb.tetradic()
            var shadess: List<Int> = argb.tints()




            when (i) {
 // 0 is monochromatic
                0 ->  {
                    FirstTextLocator.text = shadess.elementAt(3).asHex().toString().substring(3)
                    SecondTextLocator.text = shadess.elementAt(5).asHex().toString().substring(3)
                    ThirdTextLocator.text = shadess.elementAt(7).asHex().toString().substring(3)
                    FourthTextLocator.text = shadess.elementAt(8).darken(50).asHex().toString().substring(3)

                    FirstLocator.setColorFilter(shadess.elementAt(3))
                    SecondLocator.setColorFilter(shadess.elementAt(5))
                    ThirdLocator.setColorFilter(shadess.elementAt(7))
                    FourthLocator.setColorFilter(shadess.elementAt(8).darken(50))


                }

                // 1 is Complimentary
                1 -> {

                    FirstTextLocator.text = shadess.elementAt(3).asHex().toString().substring(3)
                    SecondTextLocator.text = comp.asHex().toString().substring(3)
                    ThirdTextLocator.text = shadess.elementAt(3).complimentary().asHex().toString().substring(3)
                    FourthTextLocator.text = shadess.elementAt(5).complimentary().asHex().toString().substring(3)

                    FirstLocator.setColorFilter(shadess.elementAt(3))
                    SecondLocator.setColorFilter(comp)
                    ThirdLocator.setColorFilter(shadess.elementAt(3).complimentary())
                    FourthLocator.setColorFilter(shadess.elementAt(5).complimentary())


                }
//2 is triadic
                2 -> {
                    FirstTextLocator.text = shadess.elementAt(3).asHex().toString().substring(3)
                    SecondTextLocator.text = comp.asHex().toString().substring(3)
                    ThirdTextLocator.text = tri.first.asHex().toString().substring(3)
                    FourthTextLocator.text = tri.second.asHex().toString().substring(3)


                    FirstLocator.setColorFilter(shadess.elementAt(3))
                    SecondLocator.setColorFilter(comp)
                    ThirdLocator.setColorFilter(tri.first)
                    FourthLocator.setColorFilter(tri.second)


                }
                // 3 is analogous
                3 -> {

                    FirstTextLocator.text = shadess.elementAt(3).asHex().toString().substring(3)
                    SecondTextLocator.text = comp.asHex().toString().substring(3)
                    ThirdTextLocator.text = analogous.first.asHex().toString().substring(3)
                    FourthTextLocator.text = analogous.second.asHex().toString().substring(3)


                    FirstLocator.setColorFilter(shadess.elementAt(3))
                    SecondLocator.setColorFilter(comp)
                    ThirdLocator.setColorFilter(analogous.first)
                    FourthLocator.setColorFilter(analogous.second)


                }
                //4 is tetradic
                4 -> {

                    FirstTextLocator.text = comp.asHex().toString().substring(3)
                    SecondTextLocator.text = tet.first.asHex().toString().substring(3)
                    ThirdTextLocator.text = tet.second.asHex().toString().substring(3)
                    FourthTextLocator.text = tet.third.asHex().toString().substring(3)


                    FirstLocator.setColorFilter(comp)
                    SecondLocator.setColorFilter(tet.first)
                    ThirdLocator.setColorFilter(tet.second)
                    FourthLocator.setColorFilter(tet.third)


                }

                else -> {
                    println("calling else")
                }









            }





        }

        //how do these work again

        picker.setOnItemClickedListener(this)
        picker.setOnItemSelectedListener(this)

  //this is the first listener for the wheel, changes the gradientbar triggering the other listener



        colorWheel.colorChangeListener = { rgb: Int ->

            gradientSeekBar.setBlackToColor(rgb)
            //rgbtext.text = rgb.toString()
            var wht = rgb.blue

        }





        //Inside this listener is where I'm gonna do a lot of my code. This is the constantly updating one
        gradientSeekBar.colorChangeListener = { offset: Float, argb: Int->

            var rgb = colorWheel.rgb
            setter(argb, i, rgb)
        }


        // Inputs




        fun jebsieszmato(r1:Int, g1: Int, b1: Int){

            var argb = Color.rgb(r1,g1,b1)
            var rgb = Color.rgb(r1,g1,b1)
            chosen_colour.setColorFilter(argb)
            colorWheel.rgb = argb
            gradientSeekBar.offset = 0F
            setter(argb, i, rgb)




        }


        var bitch = findViewById<Button>(R.id.setbutton)

            bitch.setOnClickListener(){


                //can be chucked

                val hex = findViewById<EditText>(R.id.hexinput)

                try {
                    var hexint = hex.text.toString()
                    var argb = parseColor(hexint)
                    var rgb = parseColor(hexint)


                    println("executing try at end")
                    colorWheel.rgb = argb
                    setter(argb, i, rgb)
                    return@setOnClickListener

                }

                catch (t: Throwable ){

                    println("throwing hex error")
                }

                // this can be chucked

                val red = findViewById<EditText>(R.id.r)
                val green = findViewById<EditText>(R.id.g)
                val blue = findViewById<EditText>(R.id.b)

                try {
                    if (red.text.toString().isEmpty()) {
                        Toast.makeText(this, "incomplete red value", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener

                    }
                    if (green.text.toString().isEmpty()) {
                        Toast.makeText(this, "incomplete green value", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener


                    }
                    if (blue.text.toString().isEmpty()) {
                        Toast.makeText(this, "incomplete blue value", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener


                    }




                    else {


                        var g1 = green.text.toString().toInt()
                        if (g1 > 255){
                            g1 = 255
                            green.setText(g1.toString())
                        }
                        var r1 = red.text.toString().toInt()
                        if (r1 > 255){
                            r1 = 255
                            red.setText(r1.toString())

                        }
                        var b1 = blue.text.toString().toInt()
                        if (b1 > 255){
                            b1 = 255
                            blue.setText(b1.toString())
                        }
                        jebsieszmato(r1, g1, b1)


                    }
                }

                catch (e: NumberFormatException){

                    Toast.makeText(this, "invalid value", Toast.LENGTH_SHORT).show()

                }




            }








    }

    override fun onItemSelected(index:Int) {
        i = index
        println(i)
        //colorWheel.rgb = colorWheel.rgb
        val bitch = findViewById<Button>(R.id.setbutton)
        val red = findViewById<EditText>(R.id.r)
        val green = findViewById<EditText>(R.id.g)
        val blue = findViewById<EditText>(R.id.b)

        try {



        if (red.text.toString().isEmpty() ){
            colorWheel.rgb = colorWheel.rgb
            return
        }
        if (green.text.toString().isEmpty() ){
            colorWheel.rgb = colorWheel.rgb
            return
        }
        if (blue.text.toString().isEmpty() ){
            colorWheel.rgb = colorWheel.rgb
            return
        }
        else

        {   var g1 = green.text.toString().toInt()
            if (g1 > 255){
                g1 = 255
                green.setText(g1.toString())
            }
            var r1 = red.text.toString().toInt()
            if (r1 > 255){
                r1 = 255
                red.setText(r1.toString())

            }
            var b1 = blue.text.toString().toInt()
            if (b1 > 255){
                b1 = 255
                blue.setText(b1.toString())
            }
            colorWheel.rgb = Color.rgb(r1,g1,b1)
            bitch.performClick()

            return}

        }

        catch (e: NumberFormatException){
            Toast.makeText(this, "invalid value", Toast.LENGTH_SHORT).show()
            return
        }
    }
    override fun onItemClicked(index:Int) {
        i = index
        println(i)
      //  colorWheel.rgb = colorWheel.rgb
        val bitch = findViewById<Button>(R.id.setbutton)


        val red = findViewById<EditText>(R.id.r)
        val green = findViewById<EditText>(R.id.g)
        val blue = findViewById<EditText>(R.id.b)

            try {


        if (red.text.toString().isEmpty() ){
            colorWheel.rgb = colorWheel.rgb
            return
        }
        if (green.text.toString().isEmpty() ){
            colorWheel.rgb = colorWheel.rgb
            return
        }
        if (blue.text.toString().isEmpty() ){
            colorWheel.rgb = colorWheel.rgb
            return
        }
        else

            {var g1 = green.text.toString().toInt()
                if (g1 > 255){
                    g1 = 255
                    green.setText(g1.toString())
                }
            var r1 = red.text.toString().toInt()
                if (r1 > 255){
                    r1 = 255
                    red.setText(r1.toString())

                }
            var b1 = blue.text.toString().toInt()
                if (b1 > 255){
                    b1 = 255
                    blue.setText(b1.toString())

                }
            colorWheel.rgb = Color.rgb(r1,g1,b1)

                bitch.performClick()


                return}

            }


            catch (e: NumberFormatException){
                Toast.makeText(this, "invalid value", Toast.LENGTH_SHORT).show()
                return
            }


    }

}


