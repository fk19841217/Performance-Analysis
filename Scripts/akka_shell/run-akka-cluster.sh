#!/bin/sh
MASTERCLASS=$1
if [ -z "$MASTERCLASS" ]
then
   MASTERCLASS="edu.leiden.aqosa.akka.AkkaCompleter"
fi

WORKERCLASS=$2
if [ -z "$WORKERCLASS" ]
then
   WORKERCLASS="edu.leiden.aqosa.akka.WorkerApp"
fi

prun -np 1 -q "all.q@node101" akkarun.sh "$WORKERCLASS" &
prun -np 1 -q "all.q@node102" akkarun.sh "$WORKERCLASS" &
prun -np 1 -q "all.q@node103" akkarun.sh "$WORKERCLASS" &
#prun -np 1 -q "all.q@node104" akkarun.sh "$WORKERCLASS" &
#prun -np 1 -q "all.q@node105" akkarun.sh "$WORKERCLASS" &

##prun -np 1 -q "all.q@node111" akkarun.sh "$MASTERCLASS" &

