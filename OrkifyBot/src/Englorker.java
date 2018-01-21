public class Englorker {
	
	private static final String CONSONANTS = "(([qwrtzpsdfghjklxcvbnm])|(ch)|(st)|(sp)|(sh)|(ph)|(th)|([tpdfgkcbw]r))";
	private static final String VOWELS = "[aeiouy]";
	private static final String DELIMITERS = "([.,;:!?]+)|(\\n)";
	private static final String REGEX_SENTENCE = "((?<=" + DELIMITERS + ")|(?=" + DELIMITERS + "))";
	private static final String REGEX_LAST_SYL = CONSONANTS + "?" + VOWELS + "+" + CONSONANTS + "*" + "\\z";
	private static final String REGEX_LAST_SYL_ALT = VOWELS + "+" + CONSONANTS + "*" + "\\z";
	
	public static String[] sentences(String text) {
		return text.split(REGEX_SENTENCE);
	}
	
	public static String orkifySentence(String sentence) {
		if (!sentence.matches("(ork)\\z")) {
			sentence = sentence.replaceAll(REGEX_LAST_SYL_ALT, "ork");
		}
		sentence = sentence.replaceAll("(Engl[a-z]*)", "Englork");
		return sentence;
	}
	
	public static String orkify(String text) {
		String out = "";
		String[] sentences = sentences(text);
		for (String sentence : sentences) {
			out += orkifySentence(sentence);
		}
		
		return out;
	}
	
}
