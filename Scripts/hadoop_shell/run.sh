#!/bin/sh
EXPFILE=$1
if [ -z "$EXPFILE" ]
then
   EXPFILE="aqosa-experiment.jar"
fi

HADOOP_CLASSPATH="$EXPFILE"
export HADOOP_CLASSPATH

prun -np 1 hadoop.sh "$EXPFILE"
