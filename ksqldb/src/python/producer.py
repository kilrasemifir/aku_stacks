from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers='localhost:9092')

# send(<topic>, <message>)
# message must be bytes array
key = 0
while (msg := input('Continuer (y/N): '))=='y':
    a = input('a: ')
    b = input('b: ')
    producer.send(
        topic='input', 
        value=bytes('{"a":'+a+',"b":'+b+'}', 'utf-8'),
        # key=bytes(str(key), 'utf-8')
    )
    key+=1