import express from "express";
import amqp from "amqplib";

const app = express();
app.use(express.json());

const exchangeName = "notifications";
let channel = null;

async function connectWithRetry() {
  while (true) {
    try {
      console.log("[API] Intentando conectar a RabbitMQ...");
      const connection = await amqp.connect("amqp://rabbitmq"); 
      channel = await connection.createChannel();
      await channel.assertExchange(exchangeName, "fanout", { durable: false });
      console.log("[API] Conectado a RabbitMQ y listo para publicar mensajes");
      break;
    } catch (err) {
      console.error(
        "[API] No se pudo conectar a RabbitMQ. Reintentando en 5s...",
        err.message
      );
      await new Promise((res) => setTimeout(res, 5000));
    }
  }
}

connectWithRetry().catch(console.error);

// Endpoint para publicar un mensaje
app.post("/publish", async (req, res) => {
  try {
    const message =
      req.body && req.body.message
        ? String(req.body.message)
        : "Mensaje vacío";

    // Si por alguna razón el canal aún no está listo
    if (!channel) {
      return res
        .status(503)
        .json({ ok: false, error: "RabbitMQ no está listo todavía" });
    }

    channel.publish(exchangeName, "", Buffer.from(message));
    console.log(`[API] Mensaje enviado: ${message}`);
    res.json({ ok: true, message });
  } catch (error) {
    console.error("[API] Error al publicar:", error);
    res.status(500).json({ ok: false, error: error.message });
  }
});

// Ruta simple de prueba
app.get("/", (req, res) => {
  res.send(
    "API Publisher funcionando. Usa POST /publish con { \"message\": \"...\" }"
  );
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () =>
  console.log(`API REST escuchando en puerto ${PORT}`)
);
