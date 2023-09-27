@echo off

echo ### Criando Queue(Standard) no SQS do LocalStack...
aws --endpoint http://localhost:4566 --profile localstack sqs create-queue --queue-name registerRecipe
aws --endpoint http://localhost:4566 --profile localstack sqs send-message --queue-url http://localhost:4566/000000000000/registerRecipe --message-body "Hello World SQS!!!" --delay-seconds 1
aws --endpoint http://localhost:4566 --profile localstack sqs receive-message --queue-url http://localhost:4566/000000000000/registerRecipe
