from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers='localhost:9092')

# send(<topic>, <message>)
# message must be bytes array
while msg := input('Message: '):
    key = input('Key: ')
    producer.send(
        topic='test', 
        value=bytes(msg, 'utf-8'),
        key=bytes(str(key), 'utf-8')
        # key=bytes(str(key), 'utf-8')
    )