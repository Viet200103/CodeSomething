#include <cstring>
#include <cmath>
#include <vector>
#include "JaroSimilarity.h"

typedef unsigned int uint;

//    0.9 trở lên: Độ tương đồng cao. Hai chuỗi được coi là rất giống nhau.
//    0.8 - 0.9: Độ tương đồng khá cao. Hai chuỗi có mức độ tương đồng đáng kể.
//    0.7 - 0.8: Độ tương đồng tốt. Hai chuỗi có sự tương đồng đáng kể, nhưng có thể có một số khác biệt.
//    Dưới 0.7: Độ tương đồng thấp. Hai chuỗi được coi là khác biệt đáng kể.

double JaroSimilarity::jaro(std::string s1, std::string s2) {

    const uint l1 = s1.length(), l2 = s2.length();
    if (l1 == 0)
        return l2 == 0 ? 1.0 : 0.0;
    const uint match_distance = std::max(l1, l2) / 2 - 1;
    bool s1_matches[l1];
    bool s2_matches[l2];
    std::fill(s1_matches, s1_matches + l1, false);
    std::fill(s2_matches, s2_matches + l2, false);
    uint matches = 0;
    for (uint i = 0; i < l1; i++) {
        const int end = std::min(i + match_distance + 1, l2);
        for (int k = std::max(0u, i - match_distance); k < end; k++)
            if (!s2_matches[k] && s1[i] == s2[k]) {
                s1_matches[i] = true;
                s2_matches[k] = true;
                matches++;
                break;
            }
    }
    if (matches == 0)
        return 0.0;
    double t = 0.0;
    uint k = 0;
    for (uint i = 0; i < l1; i++)
        if (s1_matches[i]) {
            while (!s2_matches[k]) k++;
            if (s1[i] != s2[k]) t += 0.5;
            k++;
        }

    const double m = matches;
    return (m / l1 + m / l2 + (m - t) / m) / 3.0;
}


double JaroSimilarity::jaro2(std::string str1, std::string str2) {
    size_t len1 = str1.size();
    size_t len2 = str2.size();
    if (len1 < len2) {
        std::swap(str1, str2);
        std::swap(len1, len2);
    }
    if (len2 == 0)
        return len1 == 0 ? 1.0 : 0.0;
    size_t delta = std::max(size_t(1), len1/2) - 1;
    std::vector<bool> flag(len2, false);
    std::vector<char> ch1_match;
    ch1_match.reserve(len1);
    for (size_t idx1 = 0; idx1 < len1; ++idx1) {
        char ch1 = str1[idx1];
        for (size_t idx2 = 0; idx2 < len2; ++idx2) {
            char ch2 = str2[idx2];
            if (ch1 != ch2 || flag[idx2]) continue;
            if (idx2 <= idx1 + delta && idx2 + delta >= idx1) {
                flag[idx2] = true;
                ch1_match.push_back(ch1);
                break;
            }
        }
    }
    size_t matches = ch1_match.size();
    if (matches == 0)
        return 0.0;
    size_t transpositions = 0;
    for (size_t idx1 = 0, idx2 = 0; idx2 < len2; ++idx2) {
        if (flag[idx2]) {
            if (str2[idx2] != ch1_match[idx1])
                ++transpositions;
            ++idx1;
        }
    }
    double m = matches;

    return (m/len1 + m/len2 + (m - transpositions/2.0)/m)/3.0;
}

double JaroSimilarity::remake_jaro(std::string s1, std::string s2) {

    const uint len1 = s1.length(), len2 = s2.length();

    if (len1 == 0) return len2 == 0 ? 1.0 : 0.0;


    const uint match_distance = max(len1, len2) / 2 - 1;

    bool s1_matches[len1];
    bool s2_matches[len2];

    memset(s1_matches, 0, sizeof(s1_matches));
    memset(s2_matches, 0, sizeof(s2_matches));

    uint matches = 0;

    for (int i = 0; i < len1; ++i) {
        for (int j = 0; j < len2; ++j) {
            if (!s2_matches[j] && s1[i] == s2[j]) {
                s1_matches[i] = true;
                s2_matches[j] = true;
                matches++;
                break;
            }
        }
    }

    if (matches == 0) {
        return 0.0;
    }

    double transpositions = 0.0;
    uint point = 0;

    for (int i = 0; i < len1; ++i) {
        if (s1_matches[i]) {
            while (!s2_matches[point]) point++;
            if (s1[i] != s2[point]) transpositions += 1;
            point++;
        }
    }

    const double t = transpositions / 2;
    const double m = matches;

    return (m / len1 + m / len2 + (m - t) / m) / 3.0;
}

double JaroWinkler::jaro_winkler(std::string str1, std::string str2) {

    auto jaro = JaroSimilarity::jaro(str1, str2);

    uint common_prefix = 0;

//    int max_prefix = min(4, (int) str2.size());
//
//    for (int i = 0; i < max_prefix; ++i) {
//        if (str1[i] == str2[i]) common_prefix++;
//    }

    for (int i = 0; i < min(str1.length(), str2.length()); i++) {
        if (str1[i] == str2[i]) common_prefix++;
    }

    common_prefix = min(uint(4), common_prefix);

    return jaro + common_prefix * 0.1 * (1 - jaro);
}