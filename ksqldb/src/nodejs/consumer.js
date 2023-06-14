const {Kafka} = require('kafkajs');

const kafka = new Kafka({
    clientId: 'demo-consumer',
    brokers: ['localhost:9092']
});

// Attention le group id doit être unique pour chaque consommateur
const consumer = kafka.consumer({groupId: 'demo-js-group'});


async function eachMessage({topic, partition, message}) {
    console.log({
        value: message.value.toString(),
        headers: message.headers,
    });
}

const run = async () => {
    await consumer.connect();
    await consumer.subscribe({topic: 'test'});
    await consumer.run({eachMessage});
}

run().catch(console.error);