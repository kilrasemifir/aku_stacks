const {Kafka} = require('kafkajs');
const prompt = require('prompt-sync')();

const TOPIC_NAME = 'test';

const kafka = new Kafka({
    clientId: 'demo-producer',
    brokers: ['localhost:9092']
});

const producer = kafka.producer();

const run = async () => {
    await producer.connect();
    let message = "";
    do {
        message = prompt('Votre message (vide pour stop)?');
        if (message !== '') {
            await producer.send({
                topic: TOPIC_NAME,
                messages: [
                    {value: message},
                ],
            })
        }
    }
    while (message);
    await producer.disconnect()
}

run().catch(console.error)