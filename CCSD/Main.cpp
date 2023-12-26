//
// Created by viett on 12/12/2023.
//
#include "Jaro-Winkler/JaroSimilarity.h"
#include "iostream"
#include "Levenshtein/Levenshtein.h"

int main() {
//    std::cout << JaroSimilarity::jaro("DWAYNE", "DUANE") << std::endl;
//    std::cout << JaroSimilarity::remake_jaro("ACE", "TRACE") << std::endl;
//    std::cout << JaroSimilarity::jaro2("ABCDE", "TRACE") << std::endl;
//    std::cout << JaroWinkler::jaro_winkler("CRATE", "TRACE") << std::endl;

    int lv = Levenshtein::levenshtein("hoc", "hình học");

    cout<< lv << endl;

    return 0;
}