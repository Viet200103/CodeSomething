cmake_minimum_required(VERSION 3.26)
project(CCSD)

set(CMAKE_CXX_STANDARD 17)

add_executable(
        CCSD
#        Levenshtein/LavenshteinBasic.cpp
        Main.cpp
        Jaro-Winkler/JaroSimilarity.cpp
        Jaro-Winkler/JaroSimilarity.h
        Levenshtein/Levenshtein.cpp
        Levenshtein/Levenshtein.h
)
