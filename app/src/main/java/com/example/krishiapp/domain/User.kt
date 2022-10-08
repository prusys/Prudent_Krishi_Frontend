package com.example.krishiapp.domain

class User {
    var id: Int = 0
    var phone: String = ""
    var email: String = ""
    var password: String = ""

    constructor(phone:String,email:String,password:String){
        this.email=email
        this.phone=phone
        this.password=password
    }

    constructor(){
        this.email=email
        this.phone=phone
        this.password=password
    }
}
