package com.ilya.usersupgrade

class User() {
    companion object {
        private var id: Int = 0
    }
    
    var login: String? = null
    var password: String? = null
    var name: String? = null
    var userId: Int? = null
    
    constructor(userName: String, userLogin: String, userPassword: String) : this() {
        login = userLogin
        password = userPassword
        name= userName
        userId = id++
    }
    
}