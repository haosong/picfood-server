#!/bin/bash

killall -9 java
cat /dev/null > nohup.out
git pull --rebase
mvn install -DskipTests
java -jar ./target/server-0.0.1-SNAPSHOT.jar &

