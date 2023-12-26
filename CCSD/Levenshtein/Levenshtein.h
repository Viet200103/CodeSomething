//
// Created by viett on 12/14/2023.
//

#ifndef CCSD_LEVENSHTEIN_H
#define CCSD_LEVENSHTEIN_H

#include "string"

using namespace std;

class Levenshtein {
public:
    static int levenshtein(string word1, string word2);
    static int wangerFisher(string str1, string str2);
};


#endif //CCSD_LEVENSHTEIN_H
