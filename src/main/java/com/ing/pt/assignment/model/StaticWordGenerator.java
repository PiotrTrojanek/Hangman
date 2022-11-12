package com.ing.pt.assignment.model;

import java.util.List;

public class StaticWordGenerator implements WordGenerator {

    private static final List<String> AVAILABLE_WORDS = List.of("shelf", "check", "medal", "guilt", "brown", "match", "creed",
            "quota", "tight", "basic", "Venus", "colon", "plane", "obese", "muggy", "agile", "dress", "carve", "crude",
            "bride", "dilemma", "provoke", "trouser", "silence", "splurge", "printer", "bracket", "forward", "respect",
            "warning", "fiction", "habitat", "bedroom", "tribute", "default", "diagram", "popular", "pudding",
            "tragedy", "extinct", "articulate", "vegetarian", "motivation", "brilliance", "expression", "convention",
            "domination", "mechanical", "prevalence", "hemisphere", "basketball", "simplicity", "pedestrian",
            "possession", "background", "confidence", "television", "systematic", "goalkeeper", "attractive",
            "identification", "administration", "correspondence", "rehabilitation", "recommendation", "disappointment",
            "responsibility", "superintendent", "constitutional", "representative", "discrimination", "infrastructure",
            "characteristic");

    @Override
    public String generateWord() {
        return AVAILABLE_WORDS.get((int) (Math.random() * AVAILABLE_WORDS.size()));
    }
}
