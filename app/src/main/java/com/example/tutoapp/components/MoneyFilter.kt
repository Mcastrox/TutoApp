package com.example.tutoapp.components

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Matcher
import java.util.regex.Pattern

class MoneyFilter(digitsBeforeZero:Int, digitsAfterZero:Int) : InputFilter {

    private var digitsBeforeZero = digitsBeforeZero
    private var mPattern: Pattern = Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?")

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        var matcher = mPattern.matcher(dest)
        if (!matcher.matches()){

            if (dest.toString().contains(".")) {
                if (dest.toString().substring(dest.toString().indexOf(".")).length > 2){
                    return ""
                }
                return null
            }else if (!Pattern.compile("[0-9]{0," + ( digitsBeforeZero- 1) + "}").matcher(dest).matches()) {
                if (!dest.toString().contains(".")) {
                    if (source.toString().equals(".",ignoreCase = false)) {
                        return null
                    }
                }
                return ""
            }else{
                return null
            }
        }

        return null

    }
}