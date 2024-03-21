#!/bin/bash

### TOPICS
awslocal sns create-topic --name topico_registro && \

### QUEUES
awslocal sqs create-queue --queue-name fila_registro && \
awslocal sqs create-queue --queue-name fila_relatorio && \

### SUBSCRIPTIONS
awslocal sns subscribe --topic-arn "arn:aws:sns:us-east-1:000000000000:topico_registro" \
  --protocol sqs --notification-endpoint "arn:aws:sqs:us-east-1:000000000000:fila_registro" \
  --attributes '{"RawMessageDelivery": "true", "FilterPolicy":"{\"timeClockId\":[{\"exists\": true}]}", "FilterPolicyScope":"MessageBody"}' && \

awslocal sns subscribe --topic-arn "arn:aws:sns:us-east-1:000000000000:topico_registro" \
  --protocol sqs --notification-endpoint "arn:aws:sqs:us-east-1:000000000000:fila_relatorio" \
  --attributes '{"RawMessageDelivery": "true", "FilterPolicy":"{\"month\":[{\"exists\": true}]}", "FilterPolicyScope":"MessageBody"}'
