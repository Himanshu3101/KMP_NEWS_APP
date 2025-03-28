package org.himanshu.kmp_news.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.himanshu.kmp_news.utils.AppPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingViewModel (
    private val appPreferences: AppPreferences
) : ViewModel(){
    private val _currentTheme: MutableStateFlow<String?> = MutableStateFlow(null)
    val currentTheme = _currentTheme.asStateFlow()

    init{
        currentThemeGet()
    }


    //runBlocking used when you need to wait for a coroutine to finish its execution synchronously.
    //bcoz dataStore are slow bcoz it used flow
    private fun currentThemeGet() = runBlocking{
        _currentTheme.update{
            appPreferences.getTheme()
        }
    }

    fun changeThemeMode(value:String){
        viewModelScope.launch(Dispatchers.IO) {
            appPreferences.changeTheme(value)

            _currentTheme.update{
                value
            }
        }

    }
}