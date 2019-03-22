package com.jdev.autohome.Controller

import com.jdev.autohome.Model.DoorDetail
import com.jdev.autohome.Model.Link
import com.jdev.autohome.Model.ListResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.Optional


@RestController
class DoorController {

    private var doors : MutableList<DoorDetail> = mutableListOf()

    @GetMapping(value = ["/doors"])
    @ResponseBody
    fun list(): ListResponse {
        val responseList = ListResponse()

        for (doorDetail: DoorDetail in doors){
            responseList.add(Link("door", "/door/"))
        }
        return responseList
    }

    @GetMapping(value = ["/doors/{doorId}"])
    @ResponseBody
    fun detail(@PathVariable doorId:Int):ResponseEntity<DoorDetail>{
        val optDoor: Optional<DoorDetail> = doors.stream().filter { d->d.id == doorId }.findAny()

        if (optDoor.isPresent){
            val doorDetail: DoorDetail = optDoor.get()
            return ResponseEntity(doorDetail,HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping(value = ["/doors/"])
    fun save(@RequestBody doorDetail: DoorDetail):ResponseEntity<DoorDetail>{
        val nextId = doors.size
        doorDetail.id = nextId
        doors.add(doorDetail)

        return ResponseEntity.created(URI("/door/$nextId")).build()
    }

    @PutMapping(value = ["/doors/{doorId}"])
    @ResponseBody
    fun update(@PathVariable doorId:Int, @RequestBody doorDetail: DoorDetail):ResponseEntity<DoorDetail>{
        val optDoor: Optional<DoorDetail> = doors.stream().filter { d->d.id == doorId }.findAny()

        if (optDoor.isPresent){
            doors.set(doorId-1,doorDetail)
            return ResponseEntity.accepted().build()
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
}