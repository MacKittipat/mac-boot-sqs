### Run

1. Create a new AWS IAM user with permission to read message from SQS. For example, Add the following policy `AmazonSQSReadOnlyAccess` or `AmazonSQSFullAccess` to IAM user
2. Create access key for IAM user
3. Update access key and secret in `mac-boot-sqs\src\main\resources\AwsCredentials.properties`
