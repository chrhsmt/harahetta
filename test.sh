#!/bin/bash

( echo open localhost 8810;
  sleep 3;
  echo GET SUGGESTION HARAHETTA/1.0;
  sleep 1;
  echo User-Agent: harahetta-client-1.0;
  sleep 1;
  echo location-type: address,station;
  sleep 1;
  echo location: 五反田;
  sleep 1;
  echo ; # End of Header
  sleep 1;
  echo ;
  sleep 3 ;
) | telnet #localhost 8810


  # echo location-type: geographic;
  # sleep 1;
  # echo location: 35.669220,139.761457;

  # echo location-type: phone;
  # sleep 1;
  # echo location: 0354376500;
