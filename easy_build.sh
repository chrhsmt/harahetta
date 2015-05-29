#!/bin/bash

target_dir=./target/classes/

echo "clean up ${target_dir}"
rm -rf ${target_dir}/*
echo "compiling"

mkdir -p ${target_dir}
find src -name '*.java' | xargs javac -classpath "target/dependency/*" -d ${target_dir}
find src -name '*.properties' | xargs -J % cp %  ./target/classes/

echo 'done'
