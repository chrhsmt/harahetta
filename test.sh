#!/bin/bash

( echo open localhost 8810;
  sleep 3;
  echo GET SUGGESTION HARAHETTA/1.0;
  sleep 1;
  echo User-Agent: harahetta-client-1.0;
  sleep 1;
  echo location-type: zip;
  sleep 1;
  echo location: 1000001;
  sleep 1;
  echo ; # End of Header
  sleep 1;
  echo ;
  sleep 1;
) | telnet #localhost 8810
