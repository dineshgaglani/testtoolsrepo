#!/bin/sh

#PREV_DIR=`pwd`
#SCRIPT_DIR=`dirname $0`

# exit on error
#set -e

# Call abort function if any error
#trap 'abort' EXIT

#cp ${PC_DIR}/server/tomcat/iod/saas/download/linux64/installer/agent64_install.bin agent/.
#cp ${ICSDEV_HOME}/security/root/root.pem agent/.

#echo === build icsdev/agent image
#docker build --build-arg UID=${USER_ID} --build-arg GID=${GROUP_ID} -t icsdev/agent agent

#echo === build icsdev/cloudagent image
#docker build -t icsdev/cloudagent cloudagent

#echo === Secure agent docker image generation completed successfully.

# reset trap
#trap - EXIT

#cd "$PREV_DIR"