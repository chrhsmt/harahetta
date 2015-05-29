#!/bin/bash

host=${1:-localhost}
port=${2:-8810}
keyword=${3:-SUGGESTION}
( echo open ${host} ${port};
  sleep 3;
  echo GET ${keyword} HARAHETTA/1.0;
  sleep 0.1;
  echo User-Agent: harahetta-client-1.0;
  sleep 0.1;
  echo location-type: address,station;
  sleep 0.1;
  echo location: 五反田;
  sleep 0.1;
  echo ; # End of Header
  sleep 0.1;
  echo ;
  sleep 3;
) | telnet #localhost 8810


  # echo location-type: geographic;
  # sleep 1;
  # echo location: 35.669220,139.761457;

  # echo location-type: phone;
  # sleep 1;
  # echo location: 0354376500;
