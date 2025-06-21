from flask import Flask, jsonify
from azure.cosmos import CosmosClient
import os

app = Flask(__name__)

# Variables de entorno o reemplaza directamente aquí
COSMOS_ENDPOINT = os.getenv("https://esp32.documents.azure.com:443/")
COSMOS_KEY = os.getenv("QmWUYkAPMyuk7q61dJyfOLK7X1t30dTel36xXfFRFeRDwwJwFJbC20KqRFBKA7fi9MbZWMJqgFmFACDbDlnSIg==")
DATABASE_NAME = "telemetria"
CONTAINER_NAME = "datos"

client = CosmosClient(COSMOS_ENDPOINT, credential=COSMOS_KEY)
db = client.get_database_client(DATABASE_NAME)
container = db.get_container_client(CONTAINER_NAME)

@app.route("/datos", methods=["GET"])
def get_datos():
    items = list(container.read_all_items(max_item_count=10))
    return jsonify(items)

if __name__ == "__main__":
    app.run(debug=True, port=5000)

