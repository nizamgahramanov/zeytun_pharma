package com.example.zeytunpharma.data

import androidx.room.Embedded
import androidx.room.Relation

class OrderToClient {
    @Embedded
    lateinit var client: Client

    @Relation(parentColumn = "id", entityColumn = "client_id")
    lateinit var order: Order
}