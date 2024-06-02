//
// Created by viett on 12/14/2023.
//

#include "Levenshtein.h"
#include "iostream"
#include "cstring"
#include "cmath"

int Levenshtein::levenshtein(string word1, string word2) {
    int n = (int) word1.length() + 1;
    int m = (int) word2.length() + 1;

    int dist[n][m];

    for (int i = 0; i < n; i++) {
        memset(dist[i], 0, sizeof(dist[i]));
    }

    for (int i = 0; i < n; ++i) {
        dist[i][0] = i;
    }
    for (int j = 0; j < m; ++j) {
        dist[0][j] = j;
    }


    for (int i = 1; i < n; ++i) {
        for (int j = 1; j < m; ++j) {

            int insertion_cost = dist[i][j - 1] + 1;
            int deletion_cost = dist[i - 1][j] + 1;
            int substitution_cost = dist[i - 1][j - 1] + (word1[i - 1] != word2[j - 1]);

            dist[i][j] =  min(min(insertion_cost, deletion_cost), substitution_cost);
        }
    }

    return dist[n-1][m-1];
}

int Levenshtein::wangerFisher(string str1, string str2) {

    int n = (int) str1.size() + 1;
    int m = (int) str2.size() + 1;

    int dist[m];

    for (int i = 0; i < m; ++i) {
        dist[i] = i;
    }

    // Dynamic programming loop
    for (int i = 1; i < n; ++i) {
        int prev_dist = 0; // Previous value in the current row
        for (int j = 1; j < m; ++j) {
            // Cost of insertion, deletion, and substitution
            int insertion_cost = dist[j - 1] + 1;
            int deletion_cost = dist[j] + 1;
            int substitution_cost = prev_dist + (str1[i - 1] != str2[j - 1]);

            // Minimum cost
            dist[j] = min(min(insertion_cost, deletion_cost), substitution_cost);

            // Update previous distance
            prev_dist = dist[j - 1];
        }
    }


    return dist[m - 1];
}
