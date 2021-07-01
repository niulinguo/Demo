cmake_minimum_required(VERSION 3.4.1)

aux_source_directory(
        bzip2

        BZIP_SOURCE
)

add_library(
        bspatch_utils

        SHARED

        native-lib.cpp

        bspatch.c

        ${BZIP_SOURCE}
)

include_directories(bzip2)

find_library(
        log-lib

        log
)

target_link_libraries(
        bspatch_utils

        ${log-lib}
)