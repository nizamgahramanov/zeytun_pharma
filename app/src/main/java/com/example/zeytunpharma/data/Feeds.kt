/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.zeytunpharma.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime

/**
 * A hand selected list of feeds URLs used for the purposes of displaying real information
 * in this sample app.
 */

val SampleOrderMedicines = listOf(

    OrderMedicine(3, 1, 1000),
)

@RequiresApi(Build.VERSION_CODES.O)
val SampleOrders = listOf(

    Order(
        1,
        "ZN1203669",
        "Nerimanov",
        "Primov Hakim Kərəm",
        "114, Dadasov Zavid",
        OffsetDateTime.parse("2021-11-05T12:00-06:00"),
        false,
        1
    ),
    Order(
        2,
        "ZN123132456",
        "Genclik",
        "Primov Hakim Kərəm",
        "114, Dadasov Zavid",
        OffsetDateTime.parse("2021-11-05T12:00-06:00"),
        false,
        3
    ),
    Order(
        3,
        "ZN6944499",
        "Genclik",
        "Abdullayev Xayyam",
        "36, Suleymanov Vaqif",
        OffsetDateTime.parse("2021-11-05T12:00-06:00"),
        false,
        7
    ),
    Order(
        4,
        "ZN00146999",
        "Genclik",
        "Xayyam",
        "36, Suleymanov Vaqif",
        OffsetDateTime.parse("2021-11-05T12:00-06:00"),
        false,
        2
    ),
    Order(
        5,
        "ZN1356389",
        "Nerimanov",
        "Nicat",
        "140, Səmədov Natiq",
        OffsetDateTime.parse("2021-11-04T12:00-06:00"),
        false,
        4
    ),
    Order(
        6,
        "ZN5004100",
        "Nerimanov",
        "XAYYAM",
        "140, Səmədov Natiq",
        OffsetDateTime.parse("2021-11-06T12:00-06:00"),
        false,
        5
    ),
    Order(
        7,
        "ZN8654549",
        "Nerimanov",
        "Nizam",
        "110, Məmmədov Əli",
        OffsetDateTime.parse("2021-11-06T12:00-06:00"),
        false,
        6
    ),
    Order(
        8,
        "ZN86547800",
        "GENCLIK",
        "Nizam",
        "166, Talibov Ilqar",
        OffsetDateTime.parse("2021-11-06T12:00-06:00"),
        false,
        8
    ),
)

val SampleMedicines = listOf(
    Medicine(
        1,
        "Нимесил 100 мг Н30 (Саше)(Berlin-Chemie) (Германия)",
        "I-J QABAG",
        "9149",
        "01.11.2023 04855",
        "Германия",
        1
    ),
    Medicine(
        2,
        "Грелка комбин Н1 1л(Прочее) (Киевгума ОАО) (Украина)",
        "L-1-7",
        "4217",
        "01.11.2023",
        "Украина",
        2
    ),
    Medicine(
        3,
        "Бинт 7x14 см стерильный медичинский (Прочее) (Навтекс ОАО) (Россия)",
        "H-ARXA",
        "2742",
        "01.11.2025, 13927",
        "Россия",
        3
    ),
    Medicine(
        4,
        "Аквадетрим витамин Д3 15000 МЕ/мл 10 мл (Капли) (Polfarma) (Польша)",
        "w (1)",
        "1634",
        "01.11.2023, 031120",
        "Польша",
        4
    ),
    Medicine(
        5,
        "Шприч 2мл с резбей 25GX 1mm (0.5x25mm) BERGER (Прочее) (grfg) (Китай)",
        "M-Qabagi",
        "8610",
        "01.11.2025",
        "Китай",
        2
    ),
    Medicine(
        6,
        "Цитрамон-П №6 (Таблетки) (Татхимфармпреперать) (Россия)",
        "pRS-4",
        "14273",
        "01.08.2025, 570721",
        "Россия",
        1
    ),
    Medicine(
        7,
        "Парацетрамол 0,5 гр №20 (Таблетки) (Sopharma)(Болгария)(***) ",
        "pRS-3 ",
        "9780",
        "01.10.2023 131020",
        "Болгария",
        5
    ),
    Medicine(
        8,
        "Терикс 10мг №10 (Таблетки) (Ali Raif Ilac Sanayi A.S) (Турчия) ",
        "P 9 ",
        "12750",
        "01.02.2024 21020480",
        "Турчия",
        5
    ),
)
val SampleCategories = listOf(
    Category(
        1, "4.1", "Tibbi levazimat"
    ),
    Category(
        2, "3", "Mehlullar"
    ),
    Category(
        3, "2.1", "Ampullar"
    ),
    Category(
        4, "2.2", "Melhem,Krem,Samlar"
    ),
    Category(
        5, "3.5", "Kaplusla"
    ),
)
val SampleClients = listOf(
    Client(1, "ZN000599017", "10 Qusar", "Nazim"),
    Client(2, "ZN002290861", "12 Quba", "Sahil"),
    Client(3, "ZN101150065", "33 Bakı", "Orxan"),
    Client(4, "ZN205750093", "50 Sumqayit", "Ilkin"),
    Client(5, "ZN550500609", "33 Bakı", "Orxan"),
    Client(6, "ZN10509602", "59 Xırdalan", "Pervin"),
    Client(7, "ZN69059012", "33 Bakı", "Orxan"),
    Client(8, "ZN6905000", "33 Bakı", "Orxan"),
)
val SampleUsers = listOf(
    User(1, "gahramanovnizam@gmail.com", "Baku2013"),
    User(2, "nizam@gmail.com", "Baku2014"),
    User(3, "gahramanov@gmail.com", "Baku2016"),
    User(4, "gahra@gmail.com", "2013"),
    User(5, "1234@gmail.com", "1243"),
)