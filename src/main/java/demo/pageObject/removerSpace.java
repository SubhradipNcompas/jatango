package demo.pageObject;

public class removerSpace {
    public static int removeSpaces(char[] str)
    {
        // To keep track of non-space character count
        int count = 0;

        // Traverse the given string.
        // If current character
        // is not space, then place
        // it at index 'count++'
        for (int i = 0; i<str.length; i++)
            if (str[i] != ' ')
                str[count++] = str[i]; // here count is
        // incremented

        return count;
    }
}
