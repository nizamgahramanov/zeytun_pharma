package com.example.zeytunpharma.data

import androidx.room.Embedded
import androidx.room.Relation

class CategoryToMedicine {
    @Embedded
    lateinit var category: Category

    @Relation(parentColumn = "id", entityColumn = "category_id")
    lateinit var medicine: List<Medicine>
}