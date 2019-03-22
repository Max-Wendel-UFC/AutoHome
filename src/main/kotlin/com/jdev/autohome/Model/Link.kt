package com.jdev.autohome.Model

import javax.xml.bind.annotation.XmlAttribute

class Link(val rel:String, val href:String) {

    @XmlAttribute
    fun getReal():String{
        return rel
    }

    @XmlAttribute
    fun getref():String{
        return href
    }
}