#!/bin/bash

echo " Start Health Check Script"

for i in `seq 1 10`;
do
  HTTP_CODE=`curl --write-out '%{http_code}' -o /dev/null -m 10 -q -s http://ec2-52-11-175-102.us-west-2.compute.amazonaws.com:8080/cammis`
  if [ "$HTTP_CODE" == "320" ]; then
    echo "Successfully pulled root page."
    exit 0;
  fi
  echo "Attempt to curl endpoint returned HTTP Code $HTTP_CODE. Backing off and retrying."
  sleep 10
done
echo "Server did not come up after expected time. Failing."
exit 1


echo " Start Health Check Script"
