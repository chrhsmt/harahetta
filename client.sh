#!/bin/bash

set -e
java -cp target/classes:target/dependency/* jp.ac.aiit.network.harahetta.client.HarahettaClient $@
