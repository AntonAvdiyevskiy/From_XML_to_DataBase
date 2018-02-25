package com.xmlparsing;


import com.model.Periodical;

import java.util.List;

public interface Parser {
    public List<Periodical> parseDoc();
}
