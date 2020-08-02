package com.masuwes.simplewidget.widget

import java.util.*

// this class are using to generate random number with java.util features
internal object NumberGenerator {
    fun generate(max: Int): Int {
        val random = Random()
        return random.nextInt(max)
    }
}