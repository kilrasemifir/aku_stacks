const { Kafka } = require('kafkajs')
const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
  });
  
  
const kafka = new Kafka({
  clientId: 'my-app',
  brokers: ['localhost:9092'],
})

const producer = kafka.producer()


const consumer = kafka.consumer({ groupId: 'test-js' })


const consumerRun = async () => {
    await consumer.connect()
    await consumer.subscribe({ topic: 'test', fromBeginning: true })

    await consumer.run({
        eachMessage: async ({ topic, partition, message }) => {
            console.log({
            value: message.value.toString(),
            })
        },
    })
}

producerRun().catch(console.error)
consumerRun().catch(console.error)