package display;

public class StringUtils {
    public static String[] wrap(String string, int maxWidth, int maxHeight){
        int totalRows = (int) Math.floor(string.length() / maxWidth);
        int remainder = Math.floorMod(string.length(), maxWidth);
        int maxCharacters = maxWidth * maxHeight;
        if ( maxCharacters < string.length() ){
            remainder = maxWidth;
        }
        String[] results = new String[Math.min(maxHeight, totalRows + 1)];
        for ( int x = 0; x < results.length-1; x++){
            results[x] = string.substring(x*maxWidth, (x+1)*maxWidth);
        }
        results[results.length - 1] = string.substring((results.length-1)*maxWidth, (results.length-1)*maxWidth+remainder);
        return results;
    }
}
