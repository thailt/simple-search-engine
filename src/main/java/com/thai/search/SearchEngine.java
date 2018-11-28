package com.thai.search;

import com.thai.util.StringUtil;
import java.util.List;

public interface SearchEngine {

    void index(String text);

    void index(List<String> texts);

    List<String> search(String text);

    static String[] tokenToSearchWords(String text) {
        if (StringUtil.isNullOrEmpty(text)) {
            return new String[]{};
        }
        String unAccentText = StringUtil.removeAccent(text);
        String lowerText = unAccentText.toLowerCase();
        return StringUtil.split(lowerText, 20);
    }
}
