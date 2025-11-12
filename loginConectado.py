from spyne import Application, rpc, ServiceBase, Unicode
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication
from wsgiref.simple_server import make_server
import mysql.connector

DB_CONFIG = {
    'host': '127.0.0.1',
    'database': 'proyecto_soap',
    'user': 'root',
    'password': 'Lx@&dij0978',
}

def verificar_usuario(username: str, password: str) -> str:
    conexion = None
    cursor = None
    try:
        conexion = mysql.connector.connect(**DB_CONFIG)
        cursor = conexion.cursor()
        cursor.execute("SELECT password FROM usuarios WHERE username = %s", (username,))
        fila = cursor.fetchone()
        if not fila:
            return "Credenciales incorrectas"
        if fila[0] == password:
            return f"Bienvenido, {username}"
        return "Credenciales incorrectas"
    except (Exception, mysql.connector.Error) as err:
        print(f"Error al conectar o consultar la base de datos: {err}")
        return "Error en el servidor"
    finally:
        if cursor:
            cursor.close()
        if conexion:
            conexion.close()

class loginService(ServiceBase):
    @rpc(Unicode, Unicode, _returns=Unicode)
    def login(ctx, username, password):
        return verificar_usuario(username, password)

app = Application(
    [loginService],
    tns='mi.namespace',
    in_protocol=Soap11(validator='lxml'),
    out_protocol=Soap11()
)

if __name__ == '__main__':
    server = make_server('0.0.0.0', 8001, WsgiApplication(app))
    print("Servicio SOAP en http://0.0.0.0:8001")
    server.serve_forever()