#! /bin/bash
cd ../
mvn clean install
mkdir target/dependency
cd target/dependency || exit
jar -xf ../*exec.jar
cd ../../docker || exit