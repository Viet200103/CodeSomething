//
// Created by viett on 12/12/2023.
//

#ifndef CCSD_JAROSIMILARITY_H
#define CCSD_JAROSIMILARITY_H

#include "string"

using namespace std;

//https://rosettacode.org/wiki/Jaro_similarity
class JaroSimilarity {
public:
    static double jaro(string s1, string s2);

    static double jaro2(string str1, string str2);

    static double remake_jaro(string s1, string s2);
};


class JaroWinkler {
public:
    static double jaro_winkler(string s1, string s2);
};

#endif //CCSD_JAROSIMILARITY_H
