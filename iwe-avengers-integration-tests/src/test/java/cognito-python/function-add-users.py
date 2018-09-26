import boto3

def lambda_handler(event, context):

    client = boto3.client('cognito-idp')
    
    response = client.sign_up(
        ClientId='494kpkmh3mk6rpp38c991l608m',
        Username=event['username'],
        Password=event['password'],
        UserAttributes=[
            {
                'Name': 'email',
                'Value': event['email']
            },
        ]
    )

    return {
        'message' : 'User ' + event['username'] +  ' created successfully'
    }