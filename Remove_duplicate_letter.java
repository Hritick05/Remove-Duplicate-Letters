class Solution {
    public String removeDuplicateLetters(String s) {

       int[] lastIndex = new int[26];
    for (int i = 0; i < s.length(); i++) {
        lastIndex[s.charAt(i) - 'a'] = i;
    }

    // 2. Use a boolean array to check if a character is currently in our stack
    boolean[] seen = new boolean[26];
    
    // 3. Use StringBuilder as a stack
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        int charIndex = c - 'a';

        // If we already have this character in our result, skip it
        // (because we want to keep the earlier instance if possible, 
        // unless it was popped because a smaller character came along)
        if (seen[charIndex]) continue;

        // While the last character in sb is bigger than 'c' 
        // AND that character appears later in the string:
        // Remove it to make the string lexicographically smaller
        while (sb.length() > 0 
               && sb.charAt(sb.length() - 1) > c 
               && lastIndex[sb.charAt(sb.length() - 1) - 'a'] > i) {
            
            char removed = sb.charAt(sb.length() - 1);
            seen[removed - 'a'] = false; // Mark as unseen
            sb.deleteCharAt(sb.length() - 1); // Pop from stack
        }

        // Add current character
        sb.append(c);
        seen[charIndex] = true;
    }

    return sb.toString();
    }
}