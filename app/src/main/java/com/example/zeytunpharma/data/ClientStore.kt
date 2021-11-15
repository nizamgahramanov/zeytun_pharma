package com.example.zeytunpharma.data

import com.example.zeytunpharma.data.room.ClientDAO
import com.example.zeytunpharma.data.room.TransactionRunner

class ClientStore(
    private val clientDao: ClientDAO,
    private val transactionRunner: TransactionRunner,
) {
    suspend fun addClient(client: Client): Long {
        return when (val local = clientDao.getClientWithClientId(client.client_number)) {
            null -> clientDao.insert(client)
            else -> local.id
        }
    }
}