# Notes sur la configuration du consumer:
# - Le consumer a besoin d'un group_id pour pouvoir reprendre la lecture du topic là où il s'était arrêté
from kafka import KafkaConsumer

consumer = KafkaConsumer(
    'test2', 
    bootstrap_servers='localhost:9092',
    client_id='test2-consumer',
    group_id='test2-group',
    )

for event in consumer:
    if not event.value: 
        continue
    msg = event.value.decode('utf-8')
    key = event.key
    offset = event.offset
    print(f'Key: {key} | Message: {msg} | Offset: {offset}')