const amqp = require("amqplib");
const exchangeName = "notifications";

async function connectWithRetry() {
  while (true) {
    try {
      console.log("[LOG] Intentando conectar a RabbitMQ...");
      const connection = await amqp.connect("amqp://rabbitmq");
      console.log("[LOG] Conectado a RabbitMQ");
      return connection;
    } catch (err) {
      console.error(
        "[LOG] No se pudo conectar",
        err.message
      );
      await new Promise((res) => setTimeout(res, 5000));
    }
  }
}

async function startLogSubscriber() {
  const connection = await connectWithRetry();
  const channel = await connection.createChannel();

  await channel.assertExchange(exchangeName, "fanout", { durable: false });

  const q = await channel.assertQueue("", { exclusive: true });
  console.log(`[LOG] Escuchando mensajes en la cola: ${q.queue}`);

  channel.bindQueue(q.queue, exchangeName, "");

  channel.consume(
    q.queue,
    (msg) => {
      if (msg && msg.content) {
        console.log(
          `[LOG] Registrando mensaje: '${msg.content.toString()}'`
        );
      }
    },
    { noAck: true }
  );
}

startLogSubscriber().catch(console.error);
