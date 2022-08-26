package com.example.krishiapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.replaceFragment(
    container: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.beginTransaction()
        .replace(container, fragment, fragment.javaClass.simpleName)
        .apply {
            if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
        }
        .commit()
}
fun FragmentActivity.addFragment(
    container: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.beginTransaction()
        .add(container, fragment, fragment.javaClass.simpleName)
        .apply {
            if (addToBackStack) addToBackStack(fragment.javaClass.simpleName)
        }
        .commit()
}