#!/bin/bash

myip=
while IFS=$': \t' read -a line ;do
  [ -z "${line%inet}" ] && ip=${line[${#line[1]}>4?1:2]} &&  
    [ "${ip#127.0.0.1}" ] && myip=$ip
done< <(LANG=C /sbin/ifconfig)
echo $myip

echo " Start Health Check Script"

for i in `seq 1 10`;
do
  HTTP_CODE=`curl --write-out '%{http_code}' -o /dev/null -m 10 -q -s http://$myip:8080/cammis`
  if [ "$HTTP_CODE" == "302" ]; then
    echo "Successfully pulled root page."
    exit 0;
  fi
  echo "Attempt to curl endpoint returned HTTP Code $HTTP_CODE. Backing off and retrying."
  sleep 10
done
echo "Server did not come up after expected time. Failing."
exit 1


echo " Start Health Check Script"
