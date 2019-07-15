import java.io.File
import java.io.FileNotFoundException

class ScanFile {

    fun scanIt(filepath: String, keywords: ArrayList<String>?, dKeywords: ArrayList<String>?): String {

        var stringBuilder: StringBuilder = StringBuilder()

        var lines: List<String>?

        try{
            lines = File(filepath).readLines()
        } catch (e : FileNotFoundException){
            return "File Not Found"
        }


        if (keywords?.size != 0 && dKeywords?.size != 0) {
            lines.forEach { line ->
                keywords?.forEach { keyWord ->

                    if (keyWord in line) {

                        var n = 0
                        dKeywords?.forEach { dKeyword ->
                            if (dKeyword in line) {
                                n++
                            }

                        }
                        if (n == 0) {
                            stringBuilder.append(line + "\n")
                        }
                    }
                }
            }

            return stringBuilder.toString()
        }

        if (keywords?.size != 0 && dKeywords?.size == 0) {
            lines.forEach { line ->
                keywords?.forEach { keyWord ->

                    if (keyWord in line) stringBuilder.append(line + "\n")
                }
            }

            return stringBuilder.toString()
        }

        if (keywords?.size == 0 && dKeywords?.size != 0) {
            lines.forEach { line ->
                var n = 0
                dKeywords?.forEach { dKeyword ->
                    if (dKeyword in line) n++
                }
                if (n == 0)
                    stringBuilder.append(line + "\n")
            }

            return stringBuilder.toString()
        }

        if (keywords?.size == 0 && dKeywords?.size == 0) {
            lines.forEach { line ->
                stringBuilder.append(line + "\n")
            }

            return stringBuilder.toString()
        }


        return stringBuilder.toString()
    }

    fun getContentOfFile(filepath: String): String {
        var stringBuilder: StringBuilder = StringBuilder()

        val lines: List<String> = File(filepath).readLines()
        lines.forEach {
            stringBuilder.append(it)
        }

        return stringBuilder.toString()
    }

}