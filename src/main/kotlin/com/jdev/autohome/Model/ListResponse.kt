package com.jdev.autohome.Model

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlElementWrapper

class ListResponse{

    val links:MutableList<Link> = mutableListOf()

    fun add(link: Link){
        links.add(link)
    }

    @XmlElementWrapper(name = "links")
    @XmlElement(name = "links")
    fun getLinks():Collection<Link>{
        return links
    }
}