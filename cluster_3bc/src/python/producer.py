from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers='localhost:9092')

# send(<topic>, <message>)
# message must be bytes array
key = 0
while msg := input('Message: '):
    producer.send(
        topic='test2', 
        value=bytes(msg, 'utf-8'),
        key=bytes(str(key), 'utf-8')
    )
    key+=1