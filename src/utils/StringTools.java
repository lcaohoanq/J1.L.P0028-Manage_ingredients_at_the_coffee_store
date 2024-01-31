package utils;

public class StringTools {

    //khi chuỗi có 2 khoảng trắng thừa thì sẽ xử lí còn 1
    public static String removeTwoSpace(String inp) {
        inp = inp.trim();
        return inp.replaceAll("\\s+", " ");
    }

    public static String formatRating(String rating) {
        if(rating.isEmpty()) return "";
        return rating.matches("^[01]$") ? rating.concat(" star") : rating.concat(" stars");
    }

    //format 001 -> 1, 010 -> 10, but keep 0
    public static String formatNum(String num) {
        if(num.equals("0")) return num;
        if(num.isEmpty()) return "";
        else{
            while (num.charAt(0) == '0' && num.length() > 1){
                num = num.substring(1);
            }
        }
        return num;
    }

    public static void printTitle() {
        String str = String.format("|%3s|%15s|%15s|%70s|%15s|%10s|", "ID", "Name", "Room Available", "Address", "Phone", "Rating");
        System.out.println(str);
    }

    public static void printLine() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
    }
}