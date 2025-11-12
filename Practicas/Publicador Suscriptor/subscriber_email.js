const amqp = require("amqplib");
const exchangeName = "notifications";

async function connectWithRetry() {
  while (true) {
    try {
      console.log("[EMAIL] Intentando conectar a RabbitMQ...");
      const connection = await amqp.connect("amqp://rabbitmq");
      console.log("[EMAIL] Conectado a RabbitMQ ✅");
      return connection;
    } catch (err) {
      console.error(
        "[EMAIL] No se pudo conectar a RabbitMQ. Reintentando en 5s...",
        err.message
      );
      await new Promise((res) => setTimeout(res, 5000));
    }
  }
}

async function startEmailSubscriber() {
  const connection = await connectWithRetry();
  const channel = await connection.createChannel();

  await channel.assertExchange(exchangeName, "fanout", { durable: false });

  const q = await channel.assertQueue("", { exclusive: true });
  console.log(`[EMAIL] Esperando mensajes en la cola: ${q.queue}`);

  channel.bindQueue(q.queue, exchangeName, "");

  channel.consume(
    q.queue,
    (msg) => {
      if (msg && msg.content) {
        console.log(
          `[EMAIL] Enviando correo con mensaje: '${msg.content.toString()}'`
        );
      }
    },
    { noAck: true }
  );
}

startEmailSubscriber().catch(console.error);
