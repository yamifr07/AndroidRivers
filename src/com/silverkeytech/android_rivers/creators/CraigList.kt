package com.silverkeytech.android_rivers.creators

/*
Android Rivers is an app to read and discover news using RiverJs, RSS and OPML format.
Copyright (C) 2012 Dody Gunawinata (dodyg@silverkeytech.com)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

import java.io.InputStream
import java.util.ArrayList
import java.io.BufferedReader
import java.io.InputStreamReader

public data class CraigsListCity(
    public val code : String,
    public val areaId : String,
    public val url : String,
    public val location : String,
    public val grouping : String
){
    public fun toString() : String{
        return "$code,$areaId,$url,$location,$grouping"
    }
}

public class CraigsListCityParser{
    public fun parse(input: InputStream) : ArrayList<CraigsListCity>{
        val r = BufferedReader(InputStreamReader(input))
        var x = r.readLine()

        val list = ArrayList<CraigsListCity>()

        while (x != null) {
            val city = parseLine(x!!)
            list.add(city)
            x = r.readLine()
        }

        return list
    }

    private fun parseLine(text : String) : CraigsListCity{
        val sections = text.split(",")
        val code = sections[0].trim()
        val areaId = sections[1].trim()
        val url = sections[2].trim()
        val location = sections[3].trim() + "," + sections[4].trim()
        var cat = ""
        for (i in 5..(sections.size - 1)) {
            cat += "${sections[i].trim()}, "
        }

        cat = cat.trim().replaceAll("[,]+$", "");//trim the last character at the end

        return CraigsListCity(code, areaId, url, location, cat)
    }
}

