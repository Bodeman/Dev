#!/bin/bash
# Purpose: build script for AWS code deploy
# Author: CAMMIS
# ---------------------------------------------------------------
aws configure list
aws configure set AWS_ACCESS_KEY_ID A
aws configure set AWS_SECRET_ACCESS_KEY
aws configure set default.region us-gov-west-1
aws configure list
aws deploy push --application-name SpringPOC --description "push to S3" --s3-location s3://codedeploybucket/SpringPOC.zip --source /var/lib/jenkins/workspace/SpringPOCTest/SpringPOC > createdeploy.sh
cat createdeploy.sh

_cdir="$(pwd)"
echo "working dir is : $_cdir"

sed -i -e "s/To deploy with this revision, run://g" "$_cdir/createdeploy.sh"
sed -i -e "s/<deployment-group-name>/SpringPOCDG/g" "$_cdir/createdeploy.sh"
sed -i -e "s/<deployment-config-name>/CodeDeployDefault.OneAtATime/g" "$_cdir/createdeploy.sh"
sed -i -e "s/<description>/Deployment --file-exists-behavior=OVERWRITE/g" "$_cdir/createdeploy.sh"
cat createdeploy.sh
chmod 755  createdeploy.sh
./createdeploy.sh
##rm "$_cdir"/createdeploy.sh
