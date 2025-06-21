from flask import Flask, jsonify
from azure.cosmos import CosmosClient
import os

app = Flask(__name__)

# Lee las variables de entorno
COSMOS_ENDPOINT = os.getenv("COSMOS_URL")
COSMOS_KEY = os.getenv("COSMOS_KEY")
DATABASE_NAME = "telemetria"
CONTAINER_NAME = "datos"

# Validar si están definidas
if not COSMOS_ENDPOINT or not COSMOS_KEY:
    raise Exception("❌ Faltan variables de entorno: COSMOS_URL o COSMOS_KEY")

# Cliente de Cosmos DB
client = CosmosClient(COSMOS_ENDPOINT, credential=COSMOS_KEY)
db = client.get_database_client(DATABASE_NAME)
container = db.get_container_client(CONTAINER_NAME)

@app.route("/datos", methods=["GET"])
def get_datos():
    items = list(container.read_all_items(max_item_count=10))
    return jsonify(items)

if __name__ == "__main__":
    app.run(debug=True, port=5000)

