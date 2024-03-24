#!/bin/bash

### TOPICS
awslocal sns create-topic --name registry_topic && \

### QUEUES
awslocal sqs create-queue --queue-name registry_queue && \
awslocal sqs create-queue --queue-name report_queue && \

### SUBSCRIPTIONS
awslocal sns subscribe --topic-arn "arn:aws:sns:us-east-1:000000000000:registry_topic" \
  --protocol sqs --notification-endpoint "arn:aws:sqs:us-east-1:000000000000:registry_queue" \
  --attributes '{"RawMessageDelivery": "true", "FilterPolicy":"{\"timeClockId\":[{\"exists\": true}]}", "FilterPolicyScope":"MessageBody"}' && \

awslocal sns subscribe --topic-arn "arn:aws:sns:us-east-1:000000000000:registry_topic" \
  --protocol sqs --notification-endpoint "arn:aws:sqs:us-east-1:000000000000:report_queue" \
  --attributes '{"RawMessageDelivery": "true", "FilterPolicy":"{\"yearMonth\":[{\"exists\": true}]}", "FilterPolicyScope":"MessageBody"}'
