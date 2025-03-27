public class oop {
    public static void main(String[] args) {
        System.out.println(charFreq("recitation", 'a'));
    }
    public static int charFreq(String a, char c) {
        int count = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }
}
