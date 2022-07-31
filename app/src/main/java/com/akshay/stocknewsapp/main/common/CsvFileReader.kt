package com.akshay.stocknewsapp.main.common

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.math.BigDecimal
import javax.inject.Inject

class CsvFileReader @Inject constructor() {

    fun read(inputStream: InputStream?): Map<String, List<BigDecimal>> {
        val resultMap = HashMap<String,ArrayList<BigDecimal>>()
        val reader = BufferedReader(InputStreamReader(inputStream))
        try {
            var csvLine: String?
            while (reader.readLine().also { csvLine = it } != null) {
                val row = csvLine?.split(",")?.toTypedArray() ?: arrayOf()
                if(resultMap.containsKey(row[0])){
                    val list = resultMap.getValue(row[0])
                    list.add(row[1].trim().toBigDecimal())
                    resultMap[row[0]] = list
                }
                else{
                    val list = ArrayList<BigDecimal>()
                    list.add(row[1].trim().toBigDecimal())
                    resultMap[row[0]] = list
                }

            }
        } catch (ex: IOException) {
            throw RuntimeException("Error in reading CSV file: $ex")
        } finally {
            try {
                inputStream?.close()
            } catch (e: IOException) {
                throw RuntimeException("Error while closing input stream: $e")
            }
        }
        return resultMap
    }
}